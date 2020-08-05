package xianzhan.jvmjava.java.instruction.control;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;
import xianzhan.jvmjava.java.util.NumberUtils;

/**
 * @author xianzhan
 * @since 2020-06-17
 */
public class GotoInst implements Instruction {

    public final int offset;

    public GotoInst(int offset) {
        this.offset = offset;
    }

    @Override
    public void execute(Frame frame) {
        branch(frame, offset);
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "goto " + "(" + NumberUtils.sign(offset) + offset + ")";
    }
}
