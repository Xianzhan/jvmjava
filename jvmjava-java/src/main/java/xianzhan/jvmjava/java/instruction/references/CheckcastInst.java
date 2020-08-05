package xianzhan.jvmjava.java.instruction.references;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;
import xianzhan.jvmjava.java.runtime.heap.CpClassRef;

/**
 * @author xianzhan
 * @since 2020-06-24
 */
public class CheckcastInst implements Instruction {

    private final int    index;
    private final String clazz;

    public CheckcastInst(int index, String clazz) {
        this.index = index;
        this.clazz = clazz;
    }

    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var ref = operandStack.popRef();
        operandStack.pushRef(ref);
        if (ref == null) {
            return;
        }

        var cp = frame.method().clazz().constantPool();
        var classRef = (CpClassRef) cp.getConstant(index);
        var clazz = classRef.resolvedClass();
        if (!ref.isInstanceOf(clazz)) {
            throw new ClassCastException();
        }
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "checkcast " + clazz;
    }
}
