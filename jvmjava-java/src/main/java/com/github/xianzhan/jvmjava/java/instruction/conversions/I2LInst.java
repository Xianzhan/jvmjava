package com.github.xianzhan.jvmjava.java.instruction.conversions;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-09
 */
public class I2LInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var i = operandStack.popInt();
        var l = (long) i;
        operandStack.pushLong(l);
    }
}
