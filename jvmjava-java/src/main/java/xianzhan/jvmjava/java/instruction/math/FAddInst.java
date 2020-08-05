package xianzhan.jvmjava.java.instruction.math;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * +
 *
 * @author xianzhan
 * @since 2020-06-09
 */
public class FAddInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var f1 = operandStack.popFloat();
        var f2 = operandStack.popFloat();
        var ret = f1 + f2;
        operandStack.pushFloat(ret);
    }
}
