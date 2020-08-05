package xianzhan.jvmjava.java.instruction.stores;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

import java.util.Objects;

/**
 * Store into reference array
 *
 * @author xianzhan
 * @since 2020-07-04
 */
public class AAStoreInst implements Instruction {
    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var ref = stack.popRef();
        var index = stack.popInt();
        var arrRef = stack.popRef();

        Objects.requireNonNull(arrRef);
        var refs = arrRef.refs();
        if (index < 0 || index >= refs.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        refs[index] = ref;
    }

    @Override
    public String toString() {
        return "aastore";
    }
}
