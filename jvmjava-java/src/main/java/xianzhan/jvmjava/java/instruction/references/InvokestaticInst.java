package xianzhan.jvmjava.java.instruction.references;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;
import xianzhan.jvmjava.java.runtime.heap.CpMethodRef;

/**
 * @author xianzhan
 * @since 2020-06-28
 */
public class InvokestaticInst implements Instruction {

    public final int index;

    public InvokestaticInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var cp = frame.method().clazz().constantPool();
        var methodRef = (CpMethodRef) cp.getConstant(index);
        var resolvedMethod = methodRef.resolvedMethod();
        if (!resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        var clazz = resolvedMethod.clazz();
        if (!clazz.initStarted()) {
            frame.revertNextPc();
            initClass(frame.thread(), clazz);
            return;
        }

        Instruction.invokeMethod(frame, resolvedMethod);
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "invokestatic";
    }
}
