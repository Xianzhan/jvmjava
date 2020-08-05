package xianzhan.jvmjava.java.instruction.stores;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-08
 */
public class AStore0Inst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var v = frame.operandStack().popRef();
        frame.localVars().setRef(0, v);
    }
}
