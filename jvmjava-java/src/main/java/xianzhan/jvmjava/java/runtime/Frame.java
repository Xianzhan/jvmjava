package xianzhan.jvmjava.java.runtime;

import xianzhan.jvmjava.java.runtime.heap.JMethod;

/**
 * 栈帧
 *
 * @author xianzhan
 * @since 2020-05-24
 */
public class Frame {

    private final LocalVars    localVars;
    private final OperandStack operandStack;
    private final JThread      thread;
    private final JMethod      method;
    /**
     * the next instruction after the call
     */
    private       int          nextPc;

    public Frame(JThread thread, JMethod method) {
        this.thread = thread;
        this.method = method;
        this.localVars = new LocalVars(method.maxLocals);
        this.operandStack = new OperandStack(method.maxStack);
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

    public JMethod method() {
        return method;
    }

    public int nextPc() {
        return nextPc;
    }

    public void nextPc(int nextPc) {
        this.nextPc = nextPc;
    }

    public void revertNextPc() {
        nextPc = thread.getPc();
    }

    @Override
    public String toString() {
        return "Frame#" + thread + method;
    }
}
