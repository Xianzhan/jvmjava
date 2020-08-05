package xianzhan.jvmjava.java.instruction.stores;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class DStore2Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        double v = frame.operandStack().popDouble();
        frame.localVars().setDouble(2, v);
    }
}
