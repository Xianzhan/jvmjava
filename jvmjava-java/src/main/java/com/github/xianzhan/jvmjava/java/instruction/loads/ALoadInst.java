package com.github.xianzhan.jvmjava.java.instruction.loads;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class ALoadInst implements Instruction {

    public final int index;

    public ALoadInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        Object v = frame.localVars().getRef(index);
        frame.operandStack().pushRef(v);
    }

    @Override
    public int offset() {
        return 2;
    }
}
