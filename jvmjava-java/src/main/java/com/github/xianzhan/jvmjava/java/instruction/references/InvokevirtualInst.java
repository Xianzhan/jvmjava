package com.github.xianzhan.jvmjava.java.instruction.references;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.OperandStack;
import com.github.xianzhan.jvmjava.java.runtime.heap.CpMethodRef;
import com.github.xianzhan.jvmjava.java.runtime.heap.JMethod;
import com.github.xianzhan.jvmjava.java.util.Symbol;

import java.util.Objects;

import static com.github.xianzhan.jvmjava.java.runtime.heap.CpMethodRef.lookupMethodInClass;

/**
 * Invoke instance method; dispatch based on class
 *
 * @author xianzhan
 * @since 2020-06-26
 */
public class InvokevirtualInst implements Instruction {

    public final int index;

    public InvokevirtualInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var currentClass = frame.method().clazz();
        var cp = frame.method().clazz().constantPool();
        var methodRef = (CpMethodRef) cp.getConstant(index).val;
        var resolvedMethod = methodRef.resolvedMethod();
        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        var operandStack = frame.operandStack();
        var argSlotCount = resolvedMethod.argSlotCount();
        var ref = operandStack.getRefFromTop(argSlotCount - 1);
        if (ref == null) {
            // todo hack
            if (Symbol.METHOD_PRINTLN.equals(methodRef.name())) {
                println(frame.operandStack(), methodRef.descriptor());
                return;
            }
            throw new NullPointerException();
        }

        if (resolvedMethod.isProtected() &&
            resolvedMethod.clazz().isSuperClassOf(currentClass) &&
            !Objects.equals(resolvedMethod.clazz().getPackageName(), currentClass.getPackageName()) &&
            !Objects.equals(ref.clazz(), currentClass) &&
            !ref.clazz().isSuperClassOf(currentClass)) {
            throw new IllegalAccessError();
        }

        var methodToBeInvoked = lookupMethodInClass(
                ref.clazz(), methodRef.name(), methodRef.descriptor()
        );
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }

        invokeMethod(frame, methodToBeInvoked);
    }

    private void println(OperandStack stack, String descriptor) {
        switch (descriptor) {
            case Symbol.DESCRIPTOR_BOOLEAN_V -> System.out.println(stack.popInt() != 0);
            case Symbol.DESCRIPTOR_CHAR_V -> System.out.println((char) stack.popInt());
            case Symbol.DESCRIPTOR_BYTE_V,
                    Symbol.DESCRIPTOR_SHORT_V,
                    Symbol.DESCRIPTOR_INT_V -> System.out.println(stack.popInt());
            case Symbol.DESCRIPTOR_FLOAT_V -> System.out.println(stack.popFloat());
            case Symbol.DESCRIPTOR_LONG_V -> System.out.println(stack.popLong());
            case Symbol.DESCRIPTOR_DOUBLE_V -> System.out.println(stack.popDouble());
            default -> System.out.println(descriptor);
        }
        stack.popRef();
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "invokevirtual";
    }
}
