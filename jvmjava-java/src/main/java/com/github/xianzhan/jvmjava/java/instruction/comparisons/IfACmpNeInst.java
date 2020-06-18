package com.github.xianzhan.jvmjava.java.instruction.comparisons;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-16
 */
public class IfACmpNeInst implements Instruction {

    public final int offset;

    public IfACmpNeInst(int offset) {
        this.offset = offset;
    }

    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var r2 = operandStack.popRef();
        var r1 = operandStack.popRef();

        if (r1 != r2) {
            branch(frame, offset);
        }
    }

    @Override
    public int offset() {
        return 3;
    }
}
