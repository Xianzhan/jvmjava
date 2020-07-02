package com.github.xianzhan.jvmjava.java.instruction;

import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.JThread;
import com.github.xianzhan.jvmjava.java.runtime.heap.JClass;
import com.github.xianzhan.jvmjava.java.runtime.heap.JMethod;
import com.github.xianzhan.jvmjava.java.util.Symbol;

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

    /**
     * 在定位到需要调用的方法之后，Java 虚拟机要给这个方法创建
     * 一个新的帧并把它推入 Java 虚拟机栈顶，然后传递参数。
     *
     * @param invokerFrame 栈帧
     * @param method       待调用的方法
     */
    default void invokeMethod(Frame invokerFrame, JMethod method) {
        var thread = invokerFrame.thread();
        var frame = new Frame(thread, method);
        thread.pushFrame(frame);

        var argSlotCount = method.argSlotCount();
        if (argSlotCount > 0) {
            for (int i = argSlotCount - 1; i >= 0; i--) {
                var slot = invokerFrame.operandStack().popSlot();
                frame.localVars().setSlot(i, slot);
            }
        }

        // todo hack!
        if (method.isNative()) {
            if (Symbol.METHOD_REGISTER_NATIVES.equals(method.name())) {
                thread.popFrame();
                return;
            }
            throw new RuntimeException("native method: %s.%s%s".formatted(
                    method.clazz().name,
                    method.name(),
                    method.descriptor()
            ));
        }
    }

    // 初始化 Class

    /**
     * jvm s 5.5
     *
     * @param thread 当前线程
     * @param clazz  未初始化的 class
     */
    default void initClass(JThread thread, JClass clazz) {
        clazz.startInit();
        scheduleClinit(thread, clazz);
        initSuperClass(thread, clazz);
    }

    /**
     * 类初始化
     *
     * @param thread 线程
     * @param clazz  clazz
     */
    private void scheduleClinit(JThread thread, JClass clazz) {
        var clinit = clazz.getClinitMethod();
        if (clinit != null) {
            // exec <clinit>
            var frame = new Frame(thread, clinit);
            thread.pushFrame(frame);
        }
    }

    /**
     * 初始化父类
     *
     * @param thread 线程
     * @param clazz  clazz
     */
    private void initSuperClass(JThread thread, JClass clazz) {
        if (!clazz.isInterface()) {
            var superClass = clazz.superClass;
            if (superClass != null && !superClass.initStarted()) {
                initClass(thread, superClass);
            }
        }
    }
}
