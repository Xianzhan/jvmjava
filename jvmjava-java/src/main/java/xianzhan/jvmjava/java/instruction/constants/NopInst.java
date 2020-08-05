package xianzhan.jvmjava.java.instruction.constants;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * 什么也不做
 *
 * @author xianzhan
 * @since 2020-05-31
 */
public class NopInst implements Instruction {

    @Override
    public void execute(Frame frame) {

    }
}
