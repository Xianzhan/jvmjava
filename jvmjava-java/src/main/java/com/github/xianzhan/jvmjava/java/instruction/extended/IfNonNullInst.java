package com.github.xianzhan.jvmjava.java.instruction.extended;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-17
 */
public class IfNonNullInst implements Instruction {

    public final int offset;

    public IfNonNullInst(int offset) {
        this.offset = offset;
    }

    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var ref = operandStack.popRef();

        if (ref != null) {
            branch(frame, offset);
        }
    }

    @Override
    public int offset() {
        return 3;
    }
}
