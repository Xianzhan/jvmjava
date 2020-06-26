package com.github.xianzhan.jvmjava.java.interpret;

import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.runtime.JThread;
import com.github.xianzhan.jvmjava.java.runtime.heap.JMethod;

import java.util.Map;

/**
 * 解释器
 *
 * @author xianzhan
 * @since 2020-06-17
 */
public class Interpreter {

    public void interpret(JMethod method) {
        var thread = new JThread();
        var frame = new Frame(thread, method);
        thread.pushFrame(frame);

        try {
            loop(thread, method.codeMap());
        } catch (Exception e) {
            String msg = """
                    LocalVars   : %s
                    OperandStack: %s
                    """.formatted(frame.localVars(), frame.operandStack());
            System.out.println(msg);
            throw new RuntimeException(e);
        }
    }

    private void loop(JThread thread, Map<Integer, Instruction> instructions) {
        var frame = thread.popFrame();
        int pc;

        while (true) {
            pc = frame.nextPc();
            thread.setPc(pc);

            var inst = instructions.get(pc);
            pc += inst.offset();
            frame.nextPc(pc);

            System.out.println("pc:%2d inst:%s".formatted(pc, inst));
            inst.execute(frame);
        }
    }
}
