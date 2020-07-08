package com.github.xianzhan.jvmjava.java.instruction.comparisons;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * 大于等于
 *
 * @author xianzhan
 * @since 2020-06-17
 */
public class IfGeInst implements Instruction {

    public final int offset;

    public IfGeInst(int offset) {
        this.offset = offset;
    }

    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var i = operandStack.popInt();

        if (i >= 0) {
            branch(frame, offset);
        }
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "ifge";
    }
}
