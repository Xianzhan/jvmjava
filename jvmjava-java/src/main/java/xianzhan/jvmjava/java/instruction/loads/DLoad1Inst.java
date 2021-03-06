package xianzhan.jvmjava.java.instruction.loads;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class DLoad1Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        double v = frame.localVars().getDouble(1);
        frame.operandStack().pushDouble(v);
    }
}
