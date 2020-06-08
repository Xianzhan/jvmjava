package com.github.xianzhan.jvmjava.java.instruction.constants;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-05-31
 */
public class FConst2Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        frame.operandStack().pushFloat(2.0F);
    }
}
