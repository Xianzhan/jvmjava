package com.github.xianzhan.jvmjava.java.instruction.conversions;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-09
 */
public class F2DInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var f = operandStack.popFloat();
        var d = (double) f;
        operandStack.pushDouble(d);
    }
}