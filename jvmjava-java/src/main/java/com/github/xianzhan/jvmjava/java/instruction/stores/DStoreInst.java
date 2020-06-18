package com.github.xianzhan.jvmjava.java.instruction.stores;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class DStoreInst implements Instruction {

    public final int index;

    public DStoreInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        double v = frame.operandStack().popDouble();
        frame.localVars().setDouble(index, v);
    }

    @Override
    public int offset() {
        return 2;
    }
}
