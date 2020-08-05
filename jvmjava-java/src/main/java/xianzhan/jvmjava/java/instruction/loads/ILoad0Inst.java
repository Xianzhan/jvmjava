package xianzhan.jvmjava.java.instruction.loads;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class ILoad0Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        int v = frame.localVars().getInt(0);
        frame.operandStack().pushInt(v);
    }

    @Override
    public String toString() {
        return "iload_0";
    }
}
