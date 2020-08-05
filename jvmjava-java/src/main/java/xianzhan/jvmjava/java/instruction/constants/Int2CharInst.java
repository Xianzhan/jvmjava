package xianzhan.jvmjava.java.instruction.constants;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-07-08
 */
public class Int2CharInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var i = stack.popInt();
        var c = (char) i;
        stack.pushInt(c);
    }

    @Override
    public String toString() {
        return "int2char";
    }
}
