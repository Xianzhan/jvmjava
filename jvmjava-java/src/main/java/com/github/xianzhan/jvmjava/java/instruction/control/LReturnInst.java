package com.github.xianzhan.jvmjava.java.instruction.control;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-29
 */
public class LReturnInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var thread = frame.thread();
        var currentFrame = thread.popFrame();
        var invokerFrame = thread.topFrame();
        var val = currentFrame.operandStack().popLong();
        invokerFrame.operandStack().pushLong(val);
    }

    @Override
    public String toString() {
        return "lreturn";
    }
}
