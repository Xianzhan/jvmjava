package com.github.xianzhan.jvmjava.java.instruction.math;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * iinc 指令给局部变量表中的 int 变量增加常量值，局部变量表索
 * 引和常量值都由指令的操作数提供。
 *
 * @author xianzhan
 * @since 2020-06-09
 */
public class IIncInst implements Instruction {

    public final int index;
    public final int value;

    public IIncInst(int index, int value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public void execute(Frame frame) {
        var localVars = frame.localVars();
        var inc = localVars.getInt(index);
        inc += value;
        localVars.setInt(index, inc);
    }

    @Override
    public int offset() {
        return 3;
    }
}
