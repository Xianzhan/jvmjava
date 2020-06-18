package com.github.xianzhan.jvmjava.java.instruction.math;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * -
 *
 * @author xianzhan
 * @since 2020-06-09
 */
public class DNegInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var d = operandStack.popDouble();
        operandStack.pushDouble(-d);
    }
}
