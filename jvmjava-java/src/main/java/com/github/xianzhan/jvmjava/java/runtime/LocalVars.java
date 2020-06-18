package com.github.xianzhan.jvmjava.java.runtime;

import com.github.xianzhan.jvmjava.java.util.NumberUtils;

import java.util.Arrays;

/**
 * 局部变量表
 *
 * @author xianzhan
 * @since 2020-05-24
 */
public class LocalVars {

    private final Slot[] slots;

    public LocalVars(int size) {
        this.slots = new Slot[size];
    }

    public void setInt(int index, int i) {
        slots[index] = new Slot(i, null);
    }

    public int getInt(int index) {
        return slots[index].num;
    }

    public void setFloat(int index, float f) {
        int i = Float.floatToIntBits(f);
        setInt(index, i);
    }

    public float getFloat(int index) {
        int i = getInt(index);
        return Float.intBitsToFloat(i);
    }

    public void setLong(int index, long l) {
        int high = NumberUtils.highInt(l);
        int low = NumberUtils.lowInt(l);

        setInt(index, high);
        setInt(index + 1, low);
    }

    public long getLong(int index) {
        int high = getInt(index);
        int low = getInt(index + 1);

        return NumberUtils.concatLong(high, low);
    }

    public void setDouble(int index, double d) {
        long l = Double.doubleToLongBits(d);
        setLong(index, l);
    }

    public double getDouble(int index) {
        long l = getLong(index);
        return Double.longBitsToDouble(l);
    }

    public void setRef(int index, Object ref) {
        slots[index] = new Slot(0, ref);
    }

    public Object getRef(int index) {
        return slots[index].ref;
    }

    @Override
    public String toString() {
        return Arrays.toString(slots);
    }
}
