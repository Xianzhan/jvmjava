package com.github.xianzhan.jvmjava.java.runtime;

/**
 * 栈帧
 *
 * @author xianzhan
 * @since 2020-05-24
 */
public class Frame {

    private       Frame        lower;
    private final LocalVars    localVars;
    private final OperandStack operandStack;
    private final JThread      thread;
    private       int          nextPC;

    public Frame(JThread thread, int maxLocals, int maxStack) {
        this.thread = thread;
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }

    public OperandStack operandStack() {
        return operandStack;
    }

    public LocalVars localVars() {
        return localVars;
    }

    public JThread thread() {
        return thread;
    }

    public int getNextPC() {
        return nextPC;
    }

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }
}
