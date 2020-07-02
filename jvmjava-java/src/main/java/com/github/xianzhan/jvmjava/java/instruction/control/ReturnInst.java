package com.github.xianzhan.jvmjava.java.instruction.control;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-29
 */
public class ReturnInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        frame.thread().popFrame();
    }

    @Override
    public String toString() {
        return "return_";
    }
}
