package com.github.xianzhan.jvmjava.java.instruction.references;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.heap.CpClassRef;

/**
 * @author xianzhan
 * @since 2020-07-04
 */
public class ANewArrayInst implements Instruction {

    public final int index;

    public ANewArrayInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var cp = frame.method().clazz().constantPool;
        var classRef = (CpClassRef) cp.getConstant(index);
        var componentClass = classRef.resolvedClass();

        var stack = frame.operandStack();
        var length = stack.popInt();
        if (length < 0) {
            throw new NegativeArraySizeException();
        }

        var arrClass = componentClass.arrayClass();
        var arr = arrClass.newArray(length);
        stack.pushRef(arr);
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "anewarray";
    }
}
