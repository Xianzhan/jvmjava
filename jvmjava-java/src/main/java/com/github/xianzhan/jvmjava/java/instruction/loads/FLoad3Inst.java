package com.github.xianzhan.jvmjava.java.instruction.loads;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class FLoad3Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        float v = frame.localVars().getFloat(3);
        frame.operandStack().pushFloat(v);
    }
}
