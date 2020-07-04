package com.github.xianzhan.jvmjava.java.instruction.stores;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

import java.util.Objects;

/**
 * Store into byte or boolean array
 *
 * @author xianzhan
 * @since 2020-07-04
 */
public class BAStoreInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var val = stack.popInt();
        var index = stack.popInt();
        var arrRef = stack.popRef();

        Objects.requireNonNull(arrRef);
        var bytes = arrRef.bytes();
        if (index < 0 || index >= bytes.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        bytes[index] = (byte) val;
    }

    @Override
    public String toString() {
        return "bastore";
    }
}
