package com.github.xianzhan.jvmjava.java.instruction.math;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-09
 */
public class LDivInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var l2 = operandStack.popLong();
        var l1 = operandStack.popLong();
        if (l2 == 0L) {
            throw new ArithmeticException("/ by zero");
        }
        var ret = l1 / l2;
        operandStack.pushLong(ret);
    }
}