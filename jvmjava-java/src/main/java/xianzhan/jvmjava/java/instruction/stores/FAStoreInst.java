package xianzhan.jvmjava.java.instruction.stores;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

import java.util.Objects;

/**
 * @author xianzhan
 * @since 2020-07-04
 */
public class FAStoreInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var val = stack.popFloat();
        var index = stack.popInt();
        var arrRef = stack.popRef();

        Objects.requireNonNull(arrRef);
        var floats = arrRef.floats();
        if (index < 0 || index >= floats.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        floats[index] = val;
    }

    @Override
    public String toString() {
        return "fastore";
    }
}
