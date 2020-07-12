package com.github.xianzhan.jvmjava.java.runtime.heap;

/**
 * @author xianzhan
 * @since 2020-06-24
 */
public class JConstant {

    public Object val;

    public static JConstant[] newArray(int length) {
        var arr = new JConstant[length];
        for (int i = 0; i < length; i++) {
            arr[i] = new JConstant();
        }
        return arr;
    }

    @Override
    public String toString() {
        return "JConstant#" + val;
    }
}
