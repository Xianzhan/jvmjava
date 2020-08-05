package xianzhan.jvmjava.java.instruction.loads;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class ALoad2Inst implements Instruction {

    @Override
    public void execute(Frame frame) {
        var v = frame.localVars().getRef(2);
        frame.operandStack().pushRef(v);
    }

    @Override
    public String toString() {
        return "aload_2";
    }
}
