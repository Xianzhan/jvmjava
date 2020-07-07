package com.github.xianzhan.jvmjava.java.instruction.references;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.heap.CpMethodRef;
import com.github.xianzhan.jvmjava.java.util.Symbol;

import java.util.Objects;

import static com.github.xianzhan.jvmjava.java.runtime.heap.CpMethodRef.lookupMethodInClass;

/**
 * Invoke instance method;
 * special handling for superclass, private, and instance initialization method invocations
 *
 * @author xianzhan
 * @since 2020-06-26
 */
public class InvokespecialInst implements Instruction {

    public final int index;

    public InvokespecialInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var currentClass = frame.method().clazz();
        var cp = currentClass.constantPool();
        var methodRef = (CpMethodRef) cp.getConstant(index).val;
        var resolvedClass = methodRef.resolvedClass();
        var resolvedMethod = methodRef.resolvedMethod();
        if (Symbol.METHOD_INIT.equals(resolvedMethod.name()) && !Objects.equals(resolvedMethod.clazz(), resolvedClass)) {
            throw new NoSuchMethodError();
        }
        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        var operandStack = frame.operandStack();
///        var count = resolvedMethod.argSlotCount();
        var ref = operandStack.getRefFromTop(0);
        if (ref == null) {
            // todo
            var primitive = currentClass.isPrimitive();
            System.out.println(primitive);

//            throw new NullPointerException();
        }
        if (resolvedMethod.isProtected() &&
            resolvedMethod.clazz().isSuperClassOf(currentClass) &&
            !Objects.equals(resolvedMethod.clazz().getPackageName(), currentClass.getPackageName()) &&
            !Objects.equals(ref.clazz(), currentClass) &&
            !ref.clazz().isSubClassOf(currentClass)) {
            throw new IllegalAccessError();
        }

        var methodToBeInvoked = resolvedMethod;
        if (currentClass.isSuper() &&
            resolvedClass.isSuperClassOf(currentClass) &&
            !Symbol.METHOD_INIT.equals(resolvedMethod.name())) {

            methodToBeInvoked = lookupMethodInClass(
                    currentClass.superClass, methodRef.name(), methodRef.descriptor()
            );
        }

        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }

        Instruction.invokeMethod(frame, methodToBeInvoked);
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "invokespecial";
    }
}
