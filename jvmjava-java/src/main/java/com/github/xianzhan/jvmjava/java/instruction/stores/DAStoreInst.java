package com.github.xianzhan.jvmjava.java.instruction.stores;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

import java.util.Objects;

/**
 * @author xianzhan
 * @since 2020-07-04
 */
public class DAStoreInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var val = stack.popDouble();
        var index = stack.popInt();
        var arrRef = stack.popRef();

        Objects.requireNonNull(arrRef);
        var doubles = arrRef.doubles();
        if (index < 0 || index >= doubles.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        doubles[index] = val;
    }

    @Override
    public String toString() {
        return "dastore";
    }
}
