package xianzhan.jvmjava.java.instruction.stack;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class DupInst implements Instruction {

    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var slot = operandStack.popSlot();
        operandStack.pushSlot(slot);
        operandStack.pushSlot(slot);
    }

    @Override
    public String toString() {
        return "dup";
    }
}
