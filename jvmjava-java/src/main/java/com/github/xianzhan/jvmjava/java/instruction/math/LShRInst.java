package com.github.xianzhan.jvmjava.java.instruction.math;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.util.NumberUtils;

/**
 * @author xianzhan
 * @since 2020-06-09
 */
public class LShRInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var i = operandStack.popInt();
        var l = operandStack.popLong();
        var s = i & NumberUtils.MASK_LSH;
        var ret = l >> s;
        operandStack.pushLong(ret);
    }
}
