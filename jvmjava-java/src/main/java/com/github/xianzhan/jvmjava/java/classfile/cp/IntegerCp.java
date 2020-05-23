package com.github.xianzhan.jvmjava.java.classfile.cp;

import com.github.xianzhan.jvmjava.java.classfile.ConstantInfo;

/**
 * @author xianzhan
 * @since 2020-05-17
 */
public class IntegerCp extends ConstantInfo {

    public final int val;

    public IntegerCp(int tag, int val) {
        super(tag);
        this.val = val;
    }
}
