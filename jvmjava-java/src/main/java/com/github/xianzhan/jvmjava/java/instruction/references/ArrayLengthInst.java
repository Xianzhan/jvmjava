package com.github.xianzhan.jvmjava.java.instruction.references;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * Get length of array
 *
 * @author xianzhan
 * @since 2020-07-04
 */
public class ArrayLengthInst implements Instruction {

    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var arrRef = stack.popRef();
        if (arrRef == null) {
            throw new NullPointerException();
        }

        var length = arrRef.arrayLength();
        stack.pushInt(length);
    }

    @Override
    public String toString() {
        return "arraylength";
    }
}
