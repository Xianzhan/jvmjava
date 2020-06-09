package com.github.xianzhan.jvmjava.java.instruction.stack;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * [...][d][c][b][a]
 * <p>
 * [...][b][a][d][c][b][a]
 *
 * @author xianzhan
 * @since 2020-06-08
 */
public class Dup2X2Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var slot1 = operandStack.popSlot();
        var slot2 = operandStack.popSlot();
        var slot3 = operandStack.popSlot();
        var slot4 = operandStack.popSlot();
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot4);
        operandStack.pushSlot(slot3);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
    }
}
