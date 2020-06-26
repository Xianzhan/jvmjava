package com.github.xianzhan.jvmjava.java.instruction.references;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.heap.CpMethodRef;
import com.github.xianzhan.jvmjava.java.util.Symbol;

/**
 * Invoke instance method; dispatch based on class
 *
 * @author xianzhan
 * @since 2020-06-26
 */
public class InvokevirtualInst implements Instruction {

    public final int index;

    public InvokevirtualInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        // todo
        var cp = frame.method().clazz().constantPool();
        var methodRef = (CpMethodRef) cp.getConstant(index).val;
        if ("println".equals(methodRef.name())) {
            var stack = frame.operandStack();
            switch (methodRef.descriptor()) {
                case Symbol.DESCRIPTOR_BOOLEAN_V -> System.out.println(stack.popInt() != 0);
                case Symbol.DESCRIPTOR_CHAR_V -> System.out.println((char) stack.popInt());
                case Symbol.DESCRIPTOR_BYTE_V,
                        Symbol.DESCRIPTOR_SHORT_V,
                        Symbol.DESCRIPTOR_INT_V -> System.out.println(stack.popInt());
                case Symbol.DESCRIPTOR_FLOAT_V -> System.out.println(stack.popFloat());
                case Symbol.DESCRIPTOR_LONG_V -> System.out.println(stack.popLong());
                case Symbol.DESCRIPTOR_DOUBLE_V -> System.out.println(stack.popDouble());
                default -> System.out.println(methodRef.descriptor());
            }
//            stack.popRef();
        }
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "invokevirtual";
    }
}
