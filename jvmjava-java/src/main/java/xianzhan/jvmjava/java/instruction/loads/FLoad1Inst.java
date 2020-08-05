package xianzhan.jvmjava.java.instruction.loads;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class FLoad1Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        float v = frame.localVars().getFloat(1);
        frame.operandStack().pushFloat(v);
    }
}
