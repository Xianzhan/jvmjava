package com.github.xianzhan.jvmjava.java.runtime.heap;

import com.github.xianzhan.jvmjava.java.classfile.cp.InterfaceMethodDef;

/**
 * @author xianzhan
 * @since 2020-06-24
 */
public class CpInterfaceMethodRef extends CpMethodRef {
    public CpInterfaceMethodRef(JConstantPool cp, InterfaceMethodDef refInfo) {
        super(cp, refInfo);
    }
}
