package xianzhan.jvmjava.java.instruction.math;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;
import xianzhan.jvmjava.java.util.NumberUtils;

/**
 * @author xianzhan
 * @since 2020-06-09
 */
public class LUShRInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var i = operandStack.popInt();
        var l = operandStack.popLong();
        var s = i & NumberUtils.MASK_LSH;

        if (l >= 0L) {
            var ret = l >> s;
            operandStack.pushLong(ret);
            return;
        }

        var ret = (l >> s) + (2L << ~s);
        operandStack.pushLong(ret);
    }
}
