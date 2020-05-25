package com.github.xianzhan.jvmjava.java.runtime;

/**
 * 栈帧
 *
 * @author xianzhan
 * @since 2020-05-24
 */
public class Frame {

    private Frame        lower;
    private LocalVars    localVars;
    private OperandStack operandStack;

    public Frame(int maxLocals, int maxStack) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxLocals);
    }
}
