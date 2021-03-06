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
public class GetstaticInst implements Instruction {

    public final int index;

    public GetstaticInst(int index) {
        this.index = index;
    }

    @Override
    public void execute(Frame frame) {
        var cp = frame.method().clazz().constantPool();
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

        var descriptor = field.descriptor();
        var slotIdx = field.slotIdx;
        var slots = new LocalVars(clazz.staticVars());
        var stack = frame.operandStack();

        switch (descriptor.substring(0, 1)) {
            case Symbol.DESCRIPTOR_BOOLEAN,
                    Symbol.DESCRIPTOR_BYTE,
                    Symbol.DESCRIPTOR_CHAR,
                    Symbol.DESCRIPTOR_SHORT,
                    Symbol.DESCRIPTOR_INT -> stack.pushInt(slots.getInt(slotIdx));
            case Symbol.DESCRIPTOR_FLOAT -> stack.pushFloat(slots.getFloat(slotIdx));
            case Symbol.DESCRIPTOR_LONG -> stack.pushLong(slots.getLong(slotIdx));
            case Symbol.DESCRIPTOR_DOUBLE -> stack.pushDouble(slots.getDouble(slotIdx));
            case Symbol.DESCRIPTOR_REF,
                    Symbol.DESCRIPTOR_ARR -> stack.pushRef(slots.getRef(slotIdx));
            default -> {
            }
        }
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "getstatic";
    }
}
