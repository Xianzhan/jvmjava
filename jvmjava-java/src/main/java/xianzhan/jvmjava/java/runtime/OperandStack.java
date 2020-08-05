package xianzhan.jvmjava.java.runtime;

import xianzhan.jvmjava.java.runtime.heap.JObject;
import xianzhan.jvmjava.java.util.NumberUtils;

/**
 * 操作数栈
 *
 * @author xianzhan
 * @since 2020-05-24
 */
public class OperandStack {

    private final Stack<Slot> slots;

    public OperandStack(int maxSize) {
        this.slots = new Stack<>(maxSize);
    }

    public void pushInt(int i) {
        slots.push(new Slot(i, null));
    }

    public int popInt() {
        return slots.pop().num;
    }

    public void pushFloat(float f) {
        int i = Float.floatToIntBits(f);
        pushInt(i);
    }

    public float popFloat() {
        int i = popInt();
        return Float.intBitsToFloat(i);
    }

    public void pushLong(long l) {
        int high = NumberUtils.highInt(l);
        int low = NumberUtils.lowInt(l);

        pushInt(high);
        pushInt(low);
    }

    public long popLong() {
        int low = popInt();
        int high = popInt();

        return NumberUtils.concatLong(high, low);
    }

    public void pushDouble(double d) {
        long l = Double.doubleToLongBits(d);
        pushLong(l);
    }

    public double popDouble() {
        long l = popLong();
        return Double.longBitsToDouble(l);
    }

    public void pushRef(JObject ref) {
        slots.push(new Slot(0, ref));
    }

    public JObject popRef() {
        return slots.pop().ref;
    }

    // ------ 栈指令 ------

    public void pushSlot(Slot slot) {
        slots.push(slot);
    }

    public Slot popSlot() {
        return slots.pop();
    }

    public JObject getRefFromTop(int n) {
        // 获取离栈底第 n 个的引用
        return slots.get(n).ref;
    }

    public void pushBoolean(boolean b) {
        if (b) {
            pushInt(1);
        } else {
            pushInt(0);
        }
    }

    public boolean popBoolean() {
        return popInt() == 1;
    }

    public void clear() {
        slots.clear();
    }

    @Override
    public String toString() {
        return slots.toString();
    }
}
