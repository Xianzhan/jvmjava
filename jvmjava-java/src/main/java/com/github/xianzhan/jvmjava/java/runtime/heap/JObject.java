package com.github.xianzhan.jvmjava.java.runtime.heap;

import com.github.xianzhan.jvmjava.java.runtime.LocalVars;
import com.github.xianzhan.jvmjava.java.runtime.Slot;

/**
 * 内存对象
 *
 * @author xianzhan
 * @since 2020-06-19
 */
public class JObject {

    private final JClass clazz;
    private final Slot[] fields;

    public JObject(JClass clazz) {
        this.clazz = clazz;
        this.fields = new Slot[clazz.instanceSlotCount];
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
        return new LocalVars(fields);
    }

    public boolean isInstanceOf(JClass clazz) {
        return clazz.isAssignableFrom(this.clazz);
    }
}
