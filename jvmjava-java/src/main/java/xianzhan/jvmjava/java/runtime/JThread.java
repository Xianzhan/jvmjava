package xianzhan.jvmjava.java.runtime;

import java.util.List;

/**
 * 线程
 *
 * @author xianzhan
 * @since 2020-05-24
 */
public class JThread {

    /**
     * 程序计数器
     */
    private int pc;

    /**
     * Java 虚拟机栈
     */
    private final Stack<Frame> stack;

    public JThread() {
        this(1024);
    }

    public JThread(int maxStackSize) {
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

    public Frame topFrame() {
        return stack.top();
    }

    public List<Frame> getFrames() {
        return stack.getFrames();
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public boolean isStackEmpty() {
        return stack.isEmpty();
    }

    public void clearStack() {
        stack.clear();
    }

    @Override
    public String toString() {
        return "JThread#";
    }
}
