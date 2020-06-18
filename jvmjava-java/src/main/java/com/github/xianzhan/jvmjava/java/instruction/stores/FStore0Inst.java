package com.github.xianzhan.jvmjava.java.instruction.stores;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class FStore0Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        float v = frame.operandStack().popFloat();
        frame.localVars().setFloat(0, v);
    }
}