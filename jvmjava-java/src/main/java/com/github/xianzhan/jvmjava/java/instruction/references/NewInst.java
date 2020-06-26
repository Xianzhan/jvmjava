package com.github.xianzhan.jvmjava.java.instruction.references;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.heap.CpClassRef;

/**
 * @author xianzhan
 * @since 2020-06-25
 */
public class NewInst implements Instruction {

    public final int index;

    public NewInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var cp = frame.method().clazz().constantPool();
        CpClassRef classRef = (CpClassRef) cp.getConstant(index).val;
        var clazz = classRef.resolvedClass();

        // todo init class

        if (clazz.isInterface() || clazz.isAbstract()) {
            throw new InstantiationError(clazz.name);
        }

        var ref = clazz.newObject();
        frame.operandStack().pushRef(ref);
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "new_";
    }
}
