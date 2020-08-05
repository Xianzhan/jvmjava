package xianzhan.jvmjava.java.instruction.loads;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class ILoadInst implements Instruction {

    public final int index;

    public ILoadInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        int v = frame.localVars().getInt(index);
        frame.operandStack().pushInt(v);
    }

    @Override
    public int offset() {
        // bug
        return 2;
    }

    @Override
    public String toString() {
        return "iload";
    }
}
