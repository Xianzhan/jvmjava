package xianzhan.jvmjava.java.instruction.references;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;
import xianzhan.jvmjava.java.runtime.LocalVars;
import xianzhan.jvmjava.java.runtime.heap.CpFieldRef;
import xianzhan.jvmjava.java.util.Symbol;

/**
 * @author xianzhan
 * @since 2020-06-25
 */
public class PutstaticInst implements Instruction {

    public final int index;

    public PutstaticInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var currentMethod = frame.method();
        var currentClass = currentMethod.clazz();
        var cp = currentClass.constantPool();
        var fieldRef = (CpFieldRef) cp.getConstant(index);
        var field = fieldRef.resoledField();
        var clazz = field.clazz();
        if (!clazz.initStarted()) {
            frame.revertNextPc();
            initClass(frame.thread(), clazz);
            return;
        }

        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError(field.name());
        }
        if (field.isFinal()) {
            if (currentClass != clazz || !Symbol.METHOD_CLINIT.equals(currentMethod.name())) {
                throw new IllegalAccessError(field.name());
            }
        }

        var descriptor = field.descriptor();
        var slotIdx = field.slotIdx;
        var slots = new LocalVars(clazz.staticVars());
        var stack = frame.operandStack();

        switch (descriptor) {
            case Symbol.DESCRIPTOR_BOOLEAN,
                    Symbol.DESCRIPTOR_BYTE,
                    Symbol.DESCRIPTOR_CHAR,
                    Symbol.DESCRIPTOR_SHORT,
                    Symbol.DESCRIPTOR_INT -> slots.setInt(slotIdx, stack.popInt());
            case Symbol.DESCRIPTOR_FLOAT -> slots.setFloat(slotIdx, stack.popFloat());
            case Symbol.DESCRIPTOR_LONG -> slots.setLong(slotIdx, stack.popLong());
            case Symbol.DESCRIPTOR_DOUBLE -> slots.setDouble(slotIdx, stack.popDouble());
            case Symbol.DESCRIPTOR_REF,
                    Symbol.DESCRIPTOR_ARR -> slots.setRef(slotIdx, stack.popRef());
            default -> {
                // todo
                slots.setRef(slotIdx, stack.popRef());
            }
        }
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "putstatic";
    }
}
