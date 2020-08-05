package xianzhan.jvmjava.java.instruction.stores;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class IStoreInst implements Instruction {

    public final int index;

    public IStoreInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        int v = frame.operandStack().popInt();
        frame.localVars().setInt(index, v);
    }

    @Override
    public int offset() {
        return 2;
    }

    @Override
    public String toString() {
        return "istore";
    }
}
