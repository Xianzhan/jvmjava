package xianzhan.jvmjava.java.instruction.stores;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class FStoreInst implements Instruction {

    public final int index;

    public FStoreInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        float v = frame.operandStack().popFloat();
        frame.localVars().setFloat(index, v);
    }

    @Override
    public int offset() {
        return 2;
    }
}
