package com.github.xianzhan.jvmjava.java.interpret;

import com.github.xianzhan.jvmjava.java.classloader.ClassLoader;
import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.JThread;
import com.github.xianzhan.jvmjava.java.runtime.heap.JMethod;
import com.github.xianzhan.jvmjava.java.runtime.heap.JObject;
import com.github.xianzhan.jvmjava.java.runtime.heap.StringPool;
import com.github.xianzhan.jvmjava.java.util.Symbol;

/**
 * 解释器
 *
 * @author xianzhan
 * @since 2020-06-17
 */
public class Interpreter {

    public void interpret(JMethod method, boolean isVerbose, String[] args) {
        var thread = new JThread();
        var frame = new Frame(thread, method);
        thread.pushFrame(frame);

        var jArgs = createArgsArray(method.clazz().loader, args);
        frame.localVars().setRef(0, jArgs);

        try {
            loop(thread, isVerbose);
        } catch (Exception e) {
            logFrames(thread);
            throw new RuntimeException(e);
        }
    }

    private JObject createArgsArray(ClassLoader loader, String[] args) {
        var strClass = loader.loadClass(Symbol.CLASS_STR);
        var argsArr = strClass.arrayClass().newArray(args.length);
        var jArgs = argsArr.refs();
        for (int i = 0; i < args.length; i++) {
            jArgs[i] = StringPool.jString(loader, args[i]);
        }
        return argsArr;
    }

    private void logFrames(JThread thread) {
        while (!thread.isStackEmpty()) {
            var frame = thread.popFrame();
            var method = frame.method();
            var className = method.clazz().name;
            System.out.print("""
                    >> pc:%4d %s.%s%s
                    """.formatted(frame.nextPc(),
                    className,
                    method.name(),
                    method.descriptor())
            );
        }
    }

    private void loop(JThread thread, boolean isVerbose) {
        do {
            var frame = thread.currentFrame();
            var pc = frame.nextPc();
            thread.setPc(pc);

            // decode
            var method = frame.method();
            var instMap = method.codeMap();
            var inst = instMap.get(pc);

            pc += inst.offset();
            frame.nextPc(pc);

            if (isVerbose) {
                verbose(frame, inst);
            }

            // execute
            inst.execute(frame);
        } while (!thread.isStackEmpty());
    }

    private void verbose(Frame frame, Instruction inst) {
        var method = frame.method();
        var className = method.clazz().name;
        var methodName = method.name();
        var pc = frame.thread().getPc();
        System.out.println("""
                %s.%s() #%2d %s
                """.formatted(className, methodName, pc, inst));
    }
}
