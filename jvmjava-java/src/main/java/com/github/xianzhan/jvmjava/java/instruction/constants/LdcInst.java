package com.github.xianzhan.jvmjava.java.instruction.constants;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * ldc 系列指令从运行时常量池中加载常量值，并把它推入操作数栈。
 *
 * @author xianzhan
 * @since 2020-06-26
 */
public class LdcInst implements Instruction {

    public final int index;

    public LdcInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var cp = frame.method().clazz().constantPool();
        var c = cp.getConstant(index);

        if (c.val instanceof Integer i) {
            stack.pushInt(i);
        } else if (c.val instanceof Float f) {
            stack.pushFloat(f);
        } else {
            // String
            // CpClassRef
            // MethodType, MethodHandle
            throw new RuntimeException("todo: ldc" + c);
        }
    }

    @Override
    public int offset() {
        return 2;
    }

    @Override
    public String toString() {
        return "ldc1";
    }
}
