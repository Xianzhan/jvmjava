package com.github.xianzhan.jvmjava.java.instruction.math;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * +
 *
 * @author xianzhan
 * @since 2020-06-09
 */
public class DAddInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var d1 = operandStack.popDouble();
        var d2 = operandStack.popDouble();
        var ret = d1 + d2;
        operandStack.pushDouble(ret);
    }
}
