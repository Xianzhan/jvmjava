package com.github.xianzhan.jvmjava.java.instruction.loads;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class DLoadInst implements Instruction {

    public final int index;

    public DLoadInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        double v = frame.localVars().getDouble(index);
        frame.operandStack().pushDouble(v);
    }

    @Override
    public int offset() {
        return 2;
    }
}
