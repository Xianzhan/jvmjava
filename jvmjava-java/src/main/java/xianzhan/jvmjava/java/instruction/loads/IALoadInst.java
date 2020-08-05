package xianzhan.jvmjava.java.instruction.loads;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

import java.util.Objects;

/**
 * @author xianzhan
 * @since 2020-07-04
 */
public class IALoadInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var index = stack.popInt();
        var arrRef = stack.popRef();

        Objects.requireNonNull(arrRef);
        var ints = arrRef.ints();
        if (index < 0 || index >= ints.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        stack.pushInt(ints[index]);
    }

    @Override
    public String toString() {
        return "iaload";
    }
}
