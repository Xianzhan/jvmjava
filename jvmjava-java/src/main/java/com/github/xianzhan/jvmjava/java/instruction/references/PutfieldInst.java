package com.github.xianzhan.jvmjava.java.instruction.references;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.heap.CpFieldRef;
import com.github.xianzhan.jvmjava.java.util.Symbol;

/**
 * @author xianzhan
 * @since 2020-06-25
 */
public class PutfieldInst implements Instruction {

    public final int index;

    public PutfieldInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var currentMethod = frame.method();
        var currentClass = currentMethod.clazz();
        var cp = currentClass.constantPool();
        var fieldRef = (CpFieldRef) cp.getConstant(index).val;
        var field = fieldRef.resoledField();

        if (field.isStatic()) {
            throw new IncompatibleClassChangeError(field.name());
        }
        if (field.isFinal()) {
            if (currentClass != field.clazz() || !Symbol.METHOD_INIT.equals(currentMethod.name())) {
                throw new IllegalAccessError(field.name());
            }
        }

        var descriptor = field.descriptor();
        var slotIdx = field.slotIdx;
        var stack = frame.operandStack();

        switch (descriptor.substring(0, 1)) {
            case Symbol.DESCRIPTOR_BOOLEAN,
                    Symbol.DESCRIPTOR_BYTE,
                    Symbol.DESCRIPTOR_CHAR,
                    Symbol.DESCRIPTOR_SHORT,
                    Symbol.DESCRIPTOR_INT -> {
                var i = stack.popInt();
                var ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException(field.name());
                }
                ref.fields().setInt(slotIdx, i);
            }
            case Symbol.DESCRIPTOR_FLOAT -> {
                var f = stack.popFloat();
                var ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException(field.name());
                }
                ref.fields().setFloat(slotIdx, f);
            }
            case Symbol.DESCRIPTOR_LONG -> {
                var l = stack.popLong();
                var ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException(field.name());
                }
                ref.fields().setLong(slotIdx, l);
            }
            case Symbol.DESCRIPTOR_DOUBLE -> {
                var d = stack.popDouble();
                var ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException(field.name());
                }
                ref.fields().setDouble(slotIdx, d);
            }
            case Symbol.DESCRIPTOR_REF,
                    Symbol.DESCRIPTOR_ARR -> {
                var val = stack.popRef();
                var ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException(field.name());
                }
                ref.fields().setRef(slotIdx, val);
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
        return "putfield";
    }
}
