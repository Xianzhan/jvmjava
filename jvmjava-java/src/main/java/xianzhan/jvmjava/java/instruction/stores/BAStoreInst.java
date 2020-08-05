package xianzhan.jvmjava.java.instruction.stores;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

import java.util.Objects;

/**
 * Store into byte or boolean array
 *
 * @author xianzhan
 * @since 2020-07-04
 */
public class BAStoreInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var val = stack.popInt();
        var index = stack.popInt();
        var arrRef = stack.popRef();

        Objects.requireNonNull(arrRef);
        var bytes = arrRef.bytes();
        if (index < 0 || index >= bytes.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        bytes[index] = (byte) val;
    }

    @Override
    public String toString() {
        return "bastore";
    }
}
