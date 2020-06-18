package com.github.xianzhan.jvmjava.java.interpret;

import com.github.xianzhan.jvmjava.java.classfile.Method;
import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.JThread;

/**
 * 解释器
 *
 * @author xianzhan
 * @since 2020-06-17
 */
public class Interpreter {

    public void interpret(Method method) {
        var code = method.getCode();
        var maxLocals = code.maxLocals;
        var maxStack = code.maxStack;
        var instructions = code.instructions;

        var thread = new JThread(1024);
        var frame = new Frame(thread, maxLocals, maxStack);
        thread.pushFrame(frame);

        try {
            loop(thread, instructions);
        } catch (Exception e) {
            String msg = """
                    LocalVars   : %s
                    OperandStack: %s
                    """.formatted(frame.localVars(), frame.operandStack());
            System.out.println(msg);
            throw new RuntimeException(e);
        }
    }

    private void loop(JThread thread, Instruction[] instructions) {
        var frame = thread.popFrame();

        for (var inst : instructions) {
            var pc = frame.getNextPC();
            thread.setPc(pc);

            inst.execute(frame);

            pc += inst.offset();
            frame.setNextPC(pc);
        }
    }
}
