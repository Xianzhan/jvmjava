package xianzhan.jvmjava.java.runtime.heap;

import xianzhan.jvmjava.java.runtime.LocalVars;
import xianzhan.jvmjava.java.runtime.Slot;

/**
 * 内存对象
 *
 * @author xianzhan
 * @since 2020-06-19
 */
public class JObject implements Cloneable {

    private final JClass clazz;
    /**
     * Slots for Object, []int32 for int[] ...
     */
    private final Object data;
    private       Object extra;

    public JObject(JClass clazz) {
        this.clazz = clazz;
//        this.data = Slot.newSlots(clazz.instanceSlotCount);
        this.data = new Slot[clazz.instanceSlotCount];
    }

    /**
     * 创建数组对象
     *
     * @param clazz 数组类型
     * @param data  数组
     */
    public JObject(JClass clazz, Object data) {
        this.clazz = clazz;
        this.data = data;
    }

    public JClass clazz() {
        return clazz;
    }

    /**
     * 返回 LocalVars 主要是想使用该类型的方法
     *
     * @return fields
     */
    public LocalVars fields() {
        var slots = (Slot[]) data;
        return new LocalVars(slots);
    }

    public boolean isInstanceOf(JClass clazz) {
        return clazz.isAssignableFrom(this.clazz);
    }

    // reflection

    public JObject getRefVar(String name, String descriptor) {
        var field = clazz.getField(name, descriptor, false);
        var slots = (Slot[]) data;
        return new LocalVars(slots).getRef(field.slotIdx);
    }

    public void setRefVar(String name, String descriptor, JObject ref) {
        var field = clazz.getField(name, descriptor, false);
        var slots = (Slot[]) data;
        new LocalVars(slots).setRef(field.slotIdx, ref);
    }

    public void extra(Object extra) {
        this.extra = extra;
    }

    public Object extra() {
        return extra;
    }

    // array

    public byte[] bytes() {
        return (byte[]) data;
    }

    public short[] shorts() {
        return (short[]) data;
    }

    public int[] ints() {
        return (int[]) data;
    }

    public long[] longs() {
        return (long[]) data;
    }

    public char[] chars() {
        return (char[]) data;
    }

    public float[] floats() {
        return (float[]) data;
    }

    public double[] doubles() {
        return (double[]) data;
    }

    public JObject[] refs() {
        return (JObject[]) data;
    }

    public int arrayLength() {
        int length;
        if (data instanceof byte[] bytes) {
            length = bytes.length;
        } else if (data instanceof short[] shorts) {
            length = shorts.length;
        } else if (data instanceof int[] ints) {
            length = ints.length;
        } else if (data instanceof long[] longs) {
            length = longs.length;
        } else if (data instanceof char[] chars) {
            length = chars.length;
        } else if (data instanceof float[] floats) {
            length = floats.length;
        } else if (data instanceof double[] doubles) {
            length = doubles.length;
        } else if (data instanceof JObject[] refs) {
            length = refs.length;
        } else {
            throw new RuntimeException("Not array!");
        }
        return length;
    }

    /**
     * 原始对象数据
     *
     * @return data
     */
    public Object data() {
        return data;
    }

    @Override
    public JObject clone() throws CloneNotSupportedException {
        return (JObject) super.clone();
    }

    @Override
    public String toString() {
        return "JObject#" + clazz;
    }
}
