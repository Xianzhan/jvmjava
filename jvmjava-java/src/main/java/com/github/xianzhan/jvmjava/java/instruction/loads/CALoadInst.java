package com.github.xianzhan.jvmjava.java.instruction.loads;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

import java.util.Objects;

/**
 * @author xianzhan
 * @since 2020-07-04
 */
public class CALoadInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var index = stack.popInt();
        var arrRef = stack.popRef();

        Objects.requireNonNull(arrRef);
        var chars = arrRef.chars();
        if (index < 0 || index >= chars.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        stack.pushInt(chars[index]);
    }

    @Override
    public String toString() {
        return "caload";
    }
}
