package com.github.xianzhan.jvmjava.java.instruction.loads;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class ILoad3Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        int v = frame.localVars().getInt(3);
        frame.operandStack().pushInt(v);
    }
}
