package com.github.xianzhan.jvmjava.java.instruction.loads;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

import java.util.Objects;

/**
 * Load reference from array
 *
 * @author xianzhan
 * @since 2020-07-04
 */
public class AALoadInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var index = stack.popInt();
        var arrRef = stack.popRef();

        Objects.requireNonNull(arrRef);
        var refs = arrRef.refs();
        if (index < 0 || index >= refs.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        stack.pushRef(refs[index]);
    }

    @Override
    public String toString() {
        return "aaload";
    }
}
