package com.github.xianzhan.jvmjava.java.classfile.cp;

import com.github.xianzhan.jvmjava.java.classfile.ConstantInfo;

/**
 * @author xianzhan
 * @since 2020-05-17
 */
public class ClassCp extends ConstantInfo {

    public final int nameIndex;

    public ClassCp(int tag, int nameIndex) {
        super(tag);
        this.nameIndex = nameIndex;
    }

    public String name() {
        return cp().getString(nameIndex);
    }
}
