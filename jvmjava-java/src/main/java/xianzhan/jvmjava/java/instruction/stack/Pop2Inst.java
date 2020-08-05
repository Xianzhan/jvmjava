package xianzhan.jvmjava.java.instruction.stack;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class Pop2Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        frame.operandStack().popSlot();
        frame.operandStack().popSlot();
    }
}
