package com.github.xianzhan.jvmjava.java.instruction.stores;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class IStore1Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        int v = frame.operandStack().popInt();
        frame.localVars().setInt(1, v);
    }
}
