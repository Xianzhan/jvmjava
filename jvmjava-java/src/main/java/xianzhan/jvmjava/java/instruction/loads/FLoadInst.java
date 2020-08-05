package xianzhan.jvmjava.java.instruction.loads;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class FLoadInst implements Instruction {

    public final int index;

    public FLoadInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        float v = frame.localVars().getFloat(index);
        frame.operandStack().pushFloat(v);
    }

    @Override
    public int offset() {
        return 2;
    }
}
