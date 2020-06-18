package com.github.xianzhan.jvmjava.java.instruction;

import com.github.xianzhan.jvmjava.java.runtime.Frame;

/**
 * 指令接口
 *
 * @author xianzhan
 * @since 2020-05-18
 */
public interface Instruction {

    /**
     * 执行当前栈帧上的指令
     *
     * @param frame 栈帧
     */
    void execute(Frame frame);

    /**
     * 分支跳转
     *
     * @param frame  栈帧
     * @param offset 跳转位置
     */
    default void branch(Frame frame, int offset) {
        var pc = frame.thread().getPc();
        var nextPc = pc + offset;
        frame.setNextPC(nextPc);
    }

    /**
     * 返回当前指令字节大小
     *
     * @return 当前指令字节大小
     */
    default int offset() {
        return 1;
    }
}
