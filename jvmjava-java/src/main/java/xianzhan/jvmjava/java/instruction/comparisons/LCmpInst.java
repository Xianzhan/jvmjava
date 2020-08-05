package xianzhan.jvmjava.java.instruction.comparisons;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-09
 */
public class LCmpInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var l2 = operandStack.popLong();
        var l1 = operandStack.popLong();

        if (l1 > l2) {
            operandStack.pushInt(1);
        } else if (l1 == l2) {
            operandStack.pushInt(0);
        } else {
            operandStack.pushInt(-1);
        }
    }
}
