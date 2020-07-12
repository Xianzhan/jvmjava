package com.github.xianzhan.jvmjava.java.instruction.constants;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.heap.CpClassRef;
import com.github.xianzhan.jvmjava.java.runtime.heap.StringPool;

/**
 * ldc 系列指令从运行时常量池中加载常量值，并把它推入操作数栈。
 * <p>
 * Push item from run-time constant pool
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
        var clazz = frame.method().clazz();
        var cp = clazz.constantPool();
        var c = cp.getConstant(index);

        if (c instanceof Integer i) {
            stack.pushInt(i);
        } else if (c instanceof Float f) {
            stack.pushFloat(f);
        } else if (c instanceof String str) {
            var internedStr = StringPool.jString(clazz.loader, str);
            stack.pushRef(internedStr);
        } else if (c instanceof CpClassRef classRef) {
            var classObj = classRef.resolvedClass().jClass();
            stack.pushRef(classObj);
        } else {
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
