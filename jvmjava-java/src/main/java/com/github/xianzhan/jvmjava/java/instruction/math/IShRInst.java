package com.github.xianzhan.jvmjava.java.instruction.math;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.util.NumberUtils;

/**
 * >>
 *
 * @author xianzhan
 * @since 2020-06-09
 */
public class IShRInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var i2 = operandStack.popInt();
        var i1 = operandStack.popInt();
        var s = i2 & NumberUtils.MASK_SH;
        var ret = i1 >> s;
        operandStack.pushInt(ret);
    }
}
