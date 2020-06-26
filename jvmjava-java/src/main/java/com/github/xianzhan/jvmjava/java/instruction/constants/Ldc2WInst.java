package com.github.xianzhan.jvmjava.java.instruction.constants;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @see LdcInst
 * @since 2020-06-26
 */
public class Ldc2WInst implements Instruction {

    public final int index;

    public Ldc2WInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var cp = frame.method().clazz().constantPool();
        var c = cp.getConstant(index);

        if (c.val instanceof Long l) {
            stack.pushLong(l);
        } else if (c.val instanceof Double d) {
            stack.pushDouble(d);
        } else {
            throw new ClassFormatError(c.toString());
        }
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "ldc2w";
    }
}
