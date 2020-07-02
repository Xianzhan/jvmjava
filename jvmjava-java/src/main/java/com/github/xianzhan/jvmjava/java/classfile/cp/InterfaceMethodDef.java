package com.github.xianzhan.jvmjava.java.classfile.cp;

/**
 * @author xianzhan
 * @since 2020-05-17
 */
public class InterfaceMethodDef extends MemberInfo {

    public InterfaceMethodDef(int tag, int classIndex, int nameAndTypeIndex) {
        super(tag, classIndex, nameAndTypeIndex);
    }

    @Override
    public String toString() {
        return "InterfaceMethodDef#" + className() + "." + name() + descriptor();
    }
}