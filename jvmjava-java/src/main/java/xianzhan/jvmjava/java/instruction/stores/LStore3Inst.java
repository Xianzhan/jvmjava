package xianzhan.jvmjava.java.instruction.stores;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class LStore3Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        long v = frame.operandStack().popLong();
        frame.localVars().setLong(3, v);
    }
}
