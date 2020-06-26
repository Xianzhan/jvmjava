package com.github.xianzhan.jvmjava.java.classfile.cp;

import com.github.xianzhan.jvmjava.java.classfile.ConstantInfo;

/**
 * @author xianzhan
 * @since 2020-06-24
 */
public class MemberInfo extends ConstantInfo {

    public final int classIndex;
    public final int nameAndTypeIndex;

    public MemberInfo(int tag, int classIndex, int nameAndTypeIndex) {
        super(tag);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public String className() {
        return cp().getClassName(classIndex);
    }

    public String name() {
        return cp().getNameAndType(nameAndTypeIndex)[0];
    }

    public String descriptor() {
        return cp().getNameAndType(nameAndTypeIndex)[1];
    }
}
