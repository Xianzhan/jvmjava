package com.github.xianzhan.jvmjava.java.instruction.references;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.heap.CpFieldRef;
import com.github.xianzhan.jvmjava.java.util.Symbol;

/**
 * @author xianzhan
 * @since 2020-06-25
 */
public class GetfieldInst implements Instruction {

    public final int index;

    public GetfieldInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var cp = frame.method().clazz().constantPool();
        var fieldRef = (CpFieldRef) cp.getConstant(index).val;
        var field = fieldRef.resoledField();

        if (field.isStatic()) {
            throw new IncompatibleClassChangeError(field.name());
        }

        var stack = frame.operandStack();
        var ref = stack.popRef();
        if (ref == null) {
            throw new NullPointerException();
        }

        var descriptor = field.descriptor();
        var slotIdx = field.slotIdx;
        var slots = ref.fields();

        switch (descriptor) {
            case Symbol.DESCRIPTOR_BOOLEAN,
                    Symbol.DESCRIPTOR_BYTE,
                    Symbol.DESCRIPTOR_CHAR,
                    Symbol.DESCRIPTOR_SHORT,
                    Symbol.DESCRIPTOR_INT -> {
                stack.pushInt(slots.getInt(slotIdx));
            }
            case Symbol.DESCRIPTOR_FLOAT -> {
                stack.pushFloat(slots.getFloat(slotIdx));
            }
            case Symbol.DESCRIPTOR_LONG -> {
                stack.pushLong(slots.getLong(slotIdx));
            }
            case Symbol.DESCRIPTOR_DOUBLE -> {
                stack.pushDouble(slots.getDouble(slotIdx));
            }
            case Symbol.DESCRIPTOR_REF,
                    Symbol.DESCRIPTOR_ARR -> {
                stack.pushRef(slots.getRef(slotIdx));
            }
            default -> {
            }
        }
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "getfield";
    }
}
