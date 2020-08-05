package xianzhan.jvmjava.java.instruction.stores;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class IStore0Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        int v = frame.operandStack().popInt();
        frame.localVars().setInt(0, v);
    }
}
