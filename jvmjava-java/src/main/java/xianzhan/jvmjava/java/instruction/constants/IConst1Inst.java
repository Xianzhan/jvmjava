package xianzhan.jvmjava.java.instruction.constants;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-05-31
 */
public class IConst1Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        frame.operandStack().pushInt(1);
    }

    @Override
    public String toString() {
        return "iconst_1";
    }
}
