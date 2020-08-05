package xianzhan.jvmjava.java.instruction.constants;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-07-08
 */
public class Int2ShortInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var i = stack.popInt();
        var s = (short) i;
        stack.pushInt(s);
    }

    @Override
    public String toString() {
        return "int2short";
    }
}
