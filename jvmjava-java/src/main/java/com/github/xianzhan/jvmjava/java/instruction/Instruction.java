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
        frame.nextPc(nextPc);
    }

    /**
     * 一字节的操作码 + 该操作码所需的操作数的字节数
     * <p>
     * 没有操作数的则返回 1
     * <p>
     * 操作数字节数为 1, 则返回 1 + 1
     * <p>
     * 操作数字节数为 2, 则返回 1 + 2
     * <p>
     * 操作数字节数为 4, 则返回 1 + 4
     *
     * @return 当前指令所占字节数
     */
    default int offset() {
        return 1;
    }
}
