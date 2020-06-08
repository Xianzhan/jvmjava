package com.github.xianzhan.jvmjava.java.instruction.stores;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class DStore0Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        double v = frame.operandStack().popDouble();
        frame.localVars().setDouble(0, v);
    }
}
