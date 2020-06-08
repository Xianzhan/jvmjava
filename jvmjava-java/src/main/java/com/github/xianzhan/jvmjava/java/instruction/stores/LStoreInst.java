package com.github.xianzhan.jvmjava.java.instruction.stores;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class LStoreInst implements Instruction {

    public final int index;

    public LStoreInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        long v = frame.operandStack().popLong();
        frame.localVars().setLong(index, v);
    }
}
