package com.github.xianzhan.jvmjava.java.instruction.references;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * Invoke instance method;
 * special handling for superclass, private, and instance initialization method invocations
 *
 * @author xianzhan
 * @since 2020-06-26
 */
public class InvokespecialInst implements Instruction {

    public final int index;

    public InvokespecialInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        // todo
        var stack = frame.operandStack();
        var ref = stack.popRef();
        System.out.println(ref);
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "invokespecial";
    }
}
