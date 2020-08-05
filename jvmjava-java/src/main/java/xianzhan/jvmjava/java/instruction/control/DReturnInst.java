package xianzhan.jvmjava.java.instruction.control;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-06-29
 */
public class DReturnInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var thread = frame.thread();
        var currentFrame = thread.popFrame();
        var invokerFrame = thread.topFrame();
        var val = currentFrame.operandStack().popDouble();
        invokerFrame.operandStack().pushDouble(val);
    }

    @Override
    public String toString() {
        return "dreturn";
    }
}
