package com.github.xianzhan.jvmjava.java.instruction.loads;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

import java.util.Objects;

/**
 * Load byte or boolean from array
 *
 * @author xianzhan
 * @since 2020-07-04
 */
public class BALoadInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var index = stack.popInt();
        var arrRef = stack.popRef();

        Objects.requireNonNull(arrRef);
        var bytes = arrRef.bytes();
        if (index < 0 || index >= bytes.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        stack.pushInt(bytes[index]);
    }


    @Override
    public String toString() {
        return "baload";
    }
}
