package com.github.xianzhan.jvmjava.java.instruction.loads;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class LLoad0Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        long v = frame.localVars().getLong(0);
        frame.operandStack().pushLong(v);
    }
}
