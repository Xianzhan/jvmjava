package com.github.xianzhan.jvmjava.java.runtime;

/**
 * 线程
 *
 * @author xianzhan
 * @since 2020-05-24
 */
public class Thread {

    /**
     * 程序计数器
     */
    private int pc;

    /**
     * Java 虚拟机栈
     */
    private final Stack<Frame> stack;

    public Thread(int maxStackSize) {
        this.pc = 0;
        this.stack = new Stack<>(maxStackSize);
    }

    public void pushFrame(Frame frame) {
        stack.push(frame);
    }

    public Frame popFrame() {
        return stack.pop();
    }

    public Frame currentFrame() {
        return stack.top();
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }
}
