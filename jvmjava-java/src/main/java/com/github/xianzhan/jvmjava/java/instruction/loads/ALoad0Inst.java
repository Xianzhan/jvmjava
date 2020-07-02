package com.github.xianzhan.jvmjava.java.instruction.loads;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class ALoad0Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var v = frame.localVars().getRef(0);
        frame.operandStack().pushRef(v);
    }

    @Override
    public String toString() {
        return "aload_0";
    }
}
