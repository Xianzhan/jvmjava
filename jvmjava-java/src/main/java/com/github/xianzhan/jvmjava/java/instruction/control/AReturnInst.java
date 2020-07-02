package com.github.xianzhan.jvmjava.java.instruction.control;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-29
 */
public class AReturnInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var thread = frame.thread();
        var currentFrame = thread.popFrame();
        var invokerFrame = thread.topFrame();
        var ref = currentFrame.operandStack().popRef();
        invokerFrame.operandStack().pushRef(ref);
    }

    @Override
    public String toString() {
        return "areturn";
    }
}
