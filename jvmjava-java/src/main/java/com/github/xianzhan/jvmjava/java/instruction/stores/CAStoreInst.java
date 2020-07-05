package com.github.xianzhan.jvmjava.java.instruction.stores;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

import java.util.Objects;

/**
 * @author xianzhan
 * @since 2020-07-04
 */
public class CAStoreInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var val = stack.popInt();
        var index = stack.popInt();
        var arrRef = stack.popRef();

        Objects.requireNonNull(arrRef);
        var chars = arrRef.chars();
        if (index < 0 || index >= chars.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        chars[index] = (char) val;
    }

    @Override
    public String toString() {
        return "castore";
    }
}
