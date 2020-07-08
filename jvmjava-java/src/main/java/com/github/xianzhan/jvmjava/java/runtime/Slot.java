package com.github.xianzhan.jvmjava.java.runtime;

import com.github.xianzhan.jvmjava.java.runtime.heap.JObject;

/**
 * 局部变量表插槽
 *
 * @author xianzhan
 * @since 2020-05-24
 */
public class Slot {

    public final int     num;
    public final JObject ref;

    public Slot(int num, JObject ref) {
        this.num = num;
        this.ref = ref;
    }

    public Slot(int num) {
        this.num = num;
        this.ref = null;
    }

    public Slot(JObject ref) {
        this.num = 0;
        this.ref = ref;
    }

    public static Slot[] newSlots(int length) {
        var slots = new Slot[length];
        for (int i = 0; i < length; i++) {
            slots[i] = new Slot(0);
        }
        return slots;
    }

    @Override
    public String toString() {
        return "Slot{" +
               "num=" + num +
               ", ref=" + ref +
               '}';
    }
}
