package com.github.xianzhan.jvmjava.java.runtime;

/**
 * 局部变量表插槽
 *
 * @author xianzhan
 * @since 2020-05-24
 */
public class Slot {

    public final int    num;
    public final Object ref;

    public Slot(int num, Object ref) {
        this.num = num;
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "Slot{" +
               "num=" + num +
               ", ref=" + ref +
               '}';
    }
}
