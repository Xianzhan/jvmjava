package com.github.xianzhan.jvmjava.java.instruction.loads;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class DLoad1Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        double v = frame.localVars().getDouble(1);
        frame.operandStack().pushDouble(v);
    }
}