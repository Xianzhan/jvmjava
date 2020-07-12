package com.github.xianzhan.jvmjava.java.instruction.references;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.heap.CpInterfaceMethodRef;

import static com.github.xianzhan.jvmjava.java.runtime.heap.CpMethodRef.lookupMethodInClass;

/**
 * @author xianzhan
 * @since 2020-06-28
 */
public class InvokeinterfaceInst implements Instruction {

    public final int index;
    public final int count;
    public final int zero;

    public InvokeinterfaceInst(int index, int count, int zero) {
        this.index = index;
        this.count = count;
        this.zero = zero;
    }

    @Override
    public void execute(Frame frame) {
        var cp = frame.method().clazz().constantPool();
        var methodRef = (CpInterfaceMethodRef) cp.getConstant(index);
        var resolveMethod = methodRef.resolvedInterfaceMethod();
        if (resolveMethod.isStatic() || resolveMethod.isPrivate()) {
            throw new IncompatibleClassChangeError(resolveMethod.name());
        }

///        var argSlotCount = resolveMethod.argSlotCount();
        var ref = frame.operandStack().getRefFromTop(0);
        if (ref == null) {
            // todo
            throw new NullPointerException();
        }
        if (!ref.clazz().isImplements(methodRef.resolvedClass())) {
            throw new IncompatibleClassChangeError();
        }

        var methodToBeInvoked = lookupMethodInClass(ref.clazz(), methodRef.name(), methodRef.descriptor());
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }
        if (!methodToBeInvoked.isPublic()) {
            throw new IllegalAccessError();
        }

        Instruction.invokeMethod(frame, methodToBeInvoked);
    }

    @Override
    public int offset() {
        return 5;
    }

    @Override
    public String toString() {
        return "invokeinterface";
    }
}
