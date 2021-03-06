package xianzhan.jvmjava.java.instruction.comparisons;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-17
 */
public class IfICmpLtInst implements Instruction {

    public final int offset;

    public IfICmpLtInst(int offset) {
        this.offset = offset;
    }

    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var i2 = operandStack.popInt();
        var i1 = operandStack.popInt();

        if (i1 < i2) {
            branch(frame, offset);
        }
    }

    @Override
    public int offset() {
        return 3;
    }
}
