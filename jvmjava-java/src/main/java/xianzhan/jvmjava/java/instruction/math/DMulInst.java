package xianzhan.jvmjava.java.instruction.math;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * *
 *
 * @author xianzhan
 * @since 2020-06-09
 */
public class DMulInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var d2 = operandStack.popDouble();
        var d1 = operandStack.popDouble();
        var ret = d1 * d2;
        operandStack.pushDouble(ret);
    }
}
