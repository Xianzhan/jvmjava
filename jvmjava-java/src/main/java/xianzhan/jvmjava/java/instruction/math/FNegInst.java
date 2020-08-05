package xianzhan.jvmjava.java.instruction.math;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * -
 *
 * @author xianzhan
 * @since 2020-06-09
 */
public class FNegInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var f = operandStack.popFloat();
        operandStack.pushFloat(-f);
    }
}
