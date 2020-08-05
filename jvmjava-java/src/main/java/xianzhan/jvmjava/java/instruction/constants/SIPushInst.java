package xianzhan.jvmjava.java.instruction.constants;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-05-31
 */
public class SIPushInst implements Instruction {

    public final short val;

    public SIPushInst(short val) {
        this.val = val;
    }

    @Override
    public void execute(Frame frame) {
        frame.operandStack().pushInt(val);
    }

    @Override
    public int offset() {
        return 3;
    }
}
