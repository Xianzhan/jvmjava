package xianzhan.jvmjava.java.instruction.loads;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class FLoad3Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        float v = frame.localVars().getFloat(3);
        frame.operandStack().pushFloat(v);
    }
}
