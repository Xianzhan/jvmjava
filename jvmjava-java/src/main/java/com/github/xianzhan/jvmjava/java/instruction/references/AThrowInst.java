package com.github.xianzhan.jvmjava.java.instruction.references;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.nativefunc.java.lang.Throwable;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.JThread;
import com.github.xianzhan.jvmjava.java.runtime.heap.JObject;
import com.github.xianzhan.jvmjava.java.runtime.heap.StringPool;
import com.github.xianzhan.jvmjava.java.util.Symbol;

/**
 * Throw exception or error
 *
 * @author xianzhan
 * @since 2020-07-08
 */
public class AThrowInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var ex = stack.popRef();
        if (ex == null) {
            throw new NullPointerException();
        }

        var thread = frame.thread();
        if (!findAndGotoExceptionHandler(thread, ex)) {
            handleUncaughtException(thread, ex);
        }
    }

    private boolean findAndGotoExceptionHandler(JThread thread, JObject ex) {
        do {
            var frame = thread.currentFrame();
            var pc = frame.nextPc() - 1;

            var method = frame.method();
            var handlerPc = method.findExceptionHandler(ex.clazz(), pc);
            if (handlerPc > 0) {
                var stack = frame.operandStack();
                stack.clear();
                stack.pushRef(ex);

                frame.nextPc(handlerPc);
                return true;
            }

            thread.popFrame();
        } while (!thread.isStackEmpty());

        return false;
    }

    private void handleUncaughtException(JThread thread, JObject ex) {
        thread.clearStack();

        var jMsg = ex.getRefVar(Symbol.FIELD_DETAIL_MESSAGE, Symbol.DESCRIPTOR_STR);
        var msg = StringPool.string(jMsg);
        System.err.println(ex.clazz().javaName() + ": " + msg);

        if (ex.extra() instanceof Throwable.StackTraceElement[] steArr) {
            for (var ste : steArr) {
                System.err.println("\tat " + ste);
            }
        }
    }
}
