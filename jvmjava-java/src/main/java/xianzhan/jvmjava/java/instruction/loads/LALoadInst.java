package xianzhan.jvmjava.java.instruction.loads;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

import java.util.Objects;

/**
 * @author xianzhan
 * @since 2020-07-04
 */
public class LALoadInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var index = stack.popInt();
        var arrRef = stack.popRef();

        Objects.requireNonNull(arrRef);
        var longs = arrRef.longs();
        if (index < 0 || index >= longs.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        stack.pushLong(longs[index]);
    }

    @Override
    public String toString() {
        return "laload";
    }
}
