package com.github.xianzhan.jvmjava.java.instruction.constants;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-05-31
 */
public class IConst4Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        frame.operandStack().pushInt(4);
    }
}
