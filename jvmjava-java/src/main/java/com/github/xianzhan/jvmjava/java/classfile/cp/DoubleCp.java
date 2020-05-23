package com.github.xianzhan.jvmjava.java.classfile.cp;

import com.github.xianzhan.jvmjava.java.classfile.ConstantInfo;

/**
 * @author xianzhan
 * @since 2020-05-17
 */
public class DoubleCp extends ConstantInfo {

    public final double val;

    public DoubleCp(int tag, double val) {
        super(tag);
        this.val = val;
    }
}