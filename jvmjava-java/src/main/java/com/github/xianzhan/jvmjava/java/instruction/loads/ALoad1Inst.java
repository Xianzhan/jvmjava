package com.github.xianzhan.jvmjava.java.instruction.loads;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class ALoad1Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var localVars = frame.localVars();
        var v = localVars.getRef(1);
        var stack = frame.operandStack();
        stack.pushRef(v);
    }

    @Override
    public String toString() {
        return "aload_1";
    }
}
