package com.github.xianzhan.jvmjava.java.instruction.loads;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class FLoad0Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        float v = frame.localVars().getFloat(0);
        frame.operandStack().pushFloat(v);
    }
}
