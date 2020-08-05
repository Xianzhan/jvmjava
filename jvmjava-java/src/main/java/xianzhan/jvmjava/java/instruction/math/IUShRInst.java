package xianzhan.jvmjava.java.instruction.math;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;
import xianzhan.jvmjava.java.util.NumberUtils;

/**
 * 无符号右移
 * <p>
 * >>>
 *
 * @author xianzhan
 * @since 2020-06-09
 */
public class IUShRInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var i2 = operandStack.popInt();
        var i1 = operandStack.popInt();
        var s = i2 & NumberUtils.MASK_SH;

        if (i1 >= 0) {
            var ret = i1 >> s;
            operandStack.pushInt(ret);
            return;
        }

        var ret = (i1 >> s) + (2 << ~s);
        operandStack.pushInt(ret);
    }
}
