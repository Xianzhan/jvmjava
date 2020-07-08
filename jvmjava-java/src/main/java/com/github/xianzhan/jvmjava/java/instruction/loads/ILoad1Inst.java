package com.github.xianzhan.jvmjava.java.instruction.loads;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class ILoad1Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var localVars = frame.localVars();
        int v = localVars.getInt(1);
        var stack = frame.operandStack();
        stack.pushInt(v);
    }

    @Override
    public String toString() {
        return "iload_1";
    }
}
