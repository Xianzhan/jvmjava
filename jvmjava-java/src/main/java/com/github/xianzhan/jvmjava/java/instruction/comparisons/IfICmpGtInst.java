package com.github.xianzhan.jvmjava.java.instruction.comparisons;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-17
 */
public class IfICmpGtInst implements Instruction {

    public final int offset;

    public IfICmpGtInst(int offset) {
        this.offset = offset;
    }

    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var i2 = operandStack.popInt();
        var i1 = operandStack.popInt();

        if (i1 > i2) {
            branch(frame, offset);
        }
    }

    @Override
    public int offset() {
        return 3;
    }
}
