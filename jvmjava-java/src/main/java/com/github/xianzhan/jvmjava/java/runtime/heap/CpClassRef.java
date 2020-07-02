package com.github.xianzhan.jvmjava.java.runtime.heap;

import com.github.xianzhan.jvmjava.java.classfile.cp.ClassCp;

/**
 * @author xianzhan
 * @since 2020-06-24
 */
public class CpClassRef extends CpSymRef {

    public CpClassRef(JConstantPool cp, ClassCp classInfo) {
        this.cp = cp;
        this.className = classInfo.name();
    }

    @Override
    public String toString() {
        return "CpClassRef#" + clazz;
    }
}
