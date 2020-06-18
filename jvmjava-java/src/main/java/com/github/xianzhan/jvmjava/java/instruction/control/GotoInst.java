package com.github.xianzhan.jvmjava.java.instruction.control;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-17
 */
public class GotoInst implements Instruction {

    public final int offset;

    public GotoInst(int offset) {
        this.offset = offset;
    }

    @Override
    public void execute(Frame frame) {
        branch(frame, offset);
    }

    @Override
    public int offset() {
        return 3;
    }
}
