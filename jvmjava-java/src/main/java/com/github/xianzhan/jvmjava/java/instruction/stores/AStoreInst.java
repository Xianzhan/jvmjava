package com.github.xianzhan.jvmjava.java.instruction.stores;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class AStoreInst implements Instruction {

    public final int index;

    public AStoreInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var v = frame.operandStack().popRef();
        frame.localVars().setRef(index, v);
    }

    @Override
    public int offset() {
        return 2;
    }
}
