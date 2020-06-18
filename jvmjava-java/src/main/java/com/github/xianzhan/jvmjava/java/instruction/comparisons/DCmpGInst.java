package com.github.xianzhan.jvmjava.java.instruction.comparisons;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-16
 */
public class DCmpGInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var d2 = operandStack.popDouble();
        var d1 = operandStack.popDouble();

        if (d1 > d2) {
            operandStack.pushInt(1);
        } else if (d1 == d2) {
            operandStack.pushInt(0);
        } else if (d1 < d2) {
            operandStack.pushInt(-1);
        } else {
            // NaN
            operandStack.pushInt(1);
        }
    }
}
