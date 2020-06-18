package com.github.xianzhan.jvmjava.java.instruction.comparisons;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-16
 */
public class FCmpLInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var f2 = operandStack.popFloat();
        var f1 = operandStack.popFloat();

        if (f1 > f2) {
            operandStack.pushInt(1);
        } else if (f1 == f2) {
            operandStack.pushInt(0);
        } else if (f1 < f2) {
            operandStack.pushInt(-1);
        } else {
            operandStack.pushInt(-1);
        }
    }
}
