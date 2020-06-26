package com.github.xianzhan.jvmjava.java.instruction.references;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.LocalVars;
import com.github.xianzhan.jvmjava.java.runtime.heap.CpFieldRef;
import com.github.xianzhan.jvmjava.java.util.Symbol;

/**
 * @author xianzhan
 * @since 2020-06-25
 */
public class PutstaticInst implements Instruction {

    public final int index;

    public PutstaticInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var currentMethod = frame.method();
        var currentClass = currentMethod.clazz();
        var cp = currentClass.constantPool();
        CpFieldRef fieldRef = (CpFieldRef) cp.getConstant(index).val;
        var field = fieldRef.resoledField();
        var clazz = field.clazz();

        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError(field.name());
        }
        if (field.isFinal()) {
            if (currentClass != clazz || !Symbol.METHOD_CLINIT.equals(currentMethod.name())) {
                throw new IllegalAccessError(field.name());
            }
        }

        var descriptor = field.descriptor();
        var slotIdx = field.slotIdx;
        var slots = new LocalVars(clazz.staticVars());
        var stack = frame.operandStack();

        switch (descriptor) {
            case Symbol.DESCRIPTOR_BOOLEAN,
                    Symbol.DESCRIPTOR_BYTE,
                    Symbol.DESCRIPTOR_CHAR,
                    Symbol.DESCRIPTOR_SHORT,
                    Symbol.DESCRIPTOR_INT: {
                slots.setInt(slotIdx, stack.popInt());
                break;
            }
            case Symbol.DESCRIPTOR_FLOAT: {
                slots.setFloat(slotIdx, stack.popFloat());
                break;
            }
            case Symbol.DESCRIPTOR_LONG: {
                slots.setLong(slotIdx, stack.popLong());
                break;
            }
            case Symbol.DESCRIPTOR_DOUBLE: {
                slots.setDouble(slotIdx, stack.popDouble());
                break;
            }
            case Symbol.DESCRIPTOR_REF,
                    Symbol.DESCRIPTOR_ARR: {
                slots.setRef(slotIdx, stack.popRef());
                break;
            }
            default:
        }
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "putstatic";
    }
}
