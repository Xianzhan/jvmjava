package com.github.xianzhan.jvmjava.java.classfile.cp;

/**
 * @author xianzhan
 * @since 2020-05-17
 */
public class FieldDef extends MemberInfo {

    public FieldDef(int tag, int classIndex, int nameAndTypeIndex) {
        super(tag, classIndex, nameAndTypeIndex);
    }

    @Override
    public String toString() {
        return "FieldDef#" + className() + "." + name() + ":" + descriptor();
    }
}
