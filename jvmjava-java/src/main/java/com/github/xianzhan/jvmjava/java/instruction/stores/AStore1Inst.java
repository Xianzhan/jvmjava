package com.github.xianzhan.jvmjava.java.instruction.stores;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class AStore1Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var v = frame.operandStack().popRef();
        frame.localVars().setRef(1, v);
    }

    @Override
    public String toString() {
        return "astore_1";
    }
}
