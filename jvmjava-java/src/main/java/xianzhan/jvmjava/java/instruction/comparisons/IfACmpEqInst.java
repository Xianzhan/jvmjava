package xianzhan.jvmjava.java.instruction.comparisons;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-16
 */
public class IfACmpEqInst implements Instruction {

    public final int offset;

    public IfACmpEqInst(int offset) {
        this.offset = offset;
    }

    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var r2 = operandStack.popRef();
        var r1 = operandStack.popRef();

        if (r1 == r2) {
            branch(frame, offset);
        }
    }

    @Override
    public int offset() {
        return 3;
    }
}
