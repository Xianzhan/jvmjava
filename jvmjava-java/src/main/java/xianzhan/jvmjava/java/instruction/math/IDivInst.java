package xianzhan.jvmjava.java.instruction.math;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * /
 *
 * @author xianzhan
 * @since 2020-06-09
 */
public class IDivInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var i2 = operandStack.popInt();
        var i1 = operandStack.popInt();
        if (i2 == 0) {
            throw new ArithmeticException("/ by zero");
        }
        var ret = i1 / i2;
        operandStack.pushInt(ret);
    }
}
