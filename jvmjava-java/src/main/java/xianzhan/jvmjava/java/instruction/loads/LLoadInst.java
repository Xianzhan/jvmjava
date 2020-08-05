package xianzhan.jvmjava.java.instruction.loads;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class LLoadInst implements Instruction {

    public final int index;

    public LLoadInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        long v = frame.localVars().getLong(index);
        frame.operandStack().pushLong(v);
    }

    @Override
    public int offset() {
        return 2;
    }
}
