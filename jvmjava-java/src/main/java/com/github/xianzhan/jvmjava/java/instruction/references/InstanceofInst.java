package com.github.xianzhan.jvmjava.java.instruction.references;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.heap.CpClassRef;

/**
 * @author xianzhan
 * @since 2020-06-25
 */
public class InstanceofInst implements Instruction {

    public final int index;

    public InstanceofInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var ref = stack.popRef();
        if (ref == null) {
            stack.pushInt(0);
            return;
        }

        var cp = frame.method().clazz().constantPool();
        var classRef = (CpClassRef) cp.getConstant(index);
        var clazz = classRef.resolvedClass();
        if (ref.isInstanceOf(clazz)) {
            stack.pushInt(1);
        } else {
            stack.pushInt(0);
        }
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "instanceof_";
    }
}
