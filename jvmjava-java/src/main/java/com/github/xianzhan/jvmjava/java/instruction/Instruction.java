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
}
