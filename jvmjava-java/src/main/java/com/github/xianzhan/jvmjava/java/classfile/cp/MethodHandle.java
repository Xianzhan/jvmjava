package com.github.xianzhan.jvmjava.java.classfile.cp;

import com.github.xianzhan.jvmjava.java.classfile.ConstantInfo;

/**
 * @author xianzhan
 * @since 2020-05-17
 */
public class MethodHandle extends ConstantInfo {

    public final int referenceKind;
    public final int referenceIndex;

    public MethodHandle(int tag, int referenceKind, int referenceIndex) {
        super(tag);
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }
}
