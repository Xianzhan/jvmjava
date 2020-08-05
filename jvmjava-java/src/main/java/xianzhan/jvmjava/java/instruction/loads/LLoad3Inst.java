package xianzhan.jvmjava.java.instruction.loads;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class LLoad3Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        long v = frame.localVars().getLong(3);
        frame.operandStack().pushLong(v);
    }
}
