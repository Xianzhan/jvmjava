package com.github.xianzhan.jvmjava.java.instruction.math;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-09
 */
public class ISubInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var i2 = operandStack.popInt();
        var i1 = operandStack.popInt();
        var ret = i1 - i2;
        operandStack.pushInt(ret);
    }
}
