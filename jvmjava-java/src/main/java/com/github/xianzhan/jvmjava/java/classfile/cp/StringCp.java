package com.github.xianzhan.jvmjava.java.classfile.cp;

import com.github.xianzhan.jvmjava.java.classfile.ConstantInfo;

/**
 * @author xianzhan
 * @since 2020-05-17
 */
public class StringCp extends ConstantInfo {

    public final int stringIndex;

    public StringCp(int tag, int stringIndex) {
        super(tag);
        this.stringIndex = stringIndex;
    }
}
