package com.github.xianzhan.jvmjava.java.instruction.stack;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class PopInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        frame.operandStack().popSlot();
    }
}
