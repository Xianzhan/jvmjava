package xianzhan.jvmjava.java.instruction.loads;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class ALoadInst implements Instruction {

    public final int index;

    public ALoadInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var v = frame.localVars().getRef(index);
        frame.operandStack().pushRef(v);
    }

    @Override
    public int offset() {
        return 2;
    }

    @Override
    public String toString() {
        return "aload";
    }
}
