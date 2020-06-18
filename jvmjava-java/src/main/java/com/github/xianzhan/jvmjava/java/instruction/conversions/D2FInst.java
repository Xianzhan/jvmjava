package com.github.xianzhan.jvmjava.java.instruction.conversions;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-09
 */
public class D2FInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var d = operandStack.popDouble();
        var f = (float) d;
        operandStack.pushFloat(f);
    }
}
