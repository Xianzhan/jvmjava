package com.github.xianzhan.jvmjava.java.instruction.stack;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class DupInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var slot = operandStack.popSlot();
        operandStack.pushSlot(slot);
        operandStack.pushSlot(slot);
    }
}
