package com.github.xianzhan.jvmjava.java.classfile.attribute;

import com.github.xianzhan.jvmjava.java.classfile.Attribute;

/**
 * ConstantValue_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 *     u2 constantvalue_index;
 * }
 *
 * @author xianzhan
 * @see com.github.xianzhan.jvmjava.java.classfile.ConstantInfo#CONSTANT_Integer
 * @see com.github.xianzhan.jvmjava.java.classfile.ConstantInfo#CONSTANT_Long
 * @see com.github.xianzhan.jvmjava.java.classfile.ConstantInfo#CONSTANT_Float
 * @see com.github.xianzhan.jvmjava.java.classfile.ConstantInfo#CONSTANT_Double
 * @see com.github.xianzhan.jvmjava.java.classfile.ConstantInfo#CONSTANT_String
 * @since 2020-05-23
 */
public class ConstantValue extends Attribute {

    private final int constantValueIndex;

    public ConstantValue(int constantValueIndex) {
        this.constantValueIndex = constantValueIndex;
    }

    public int constantValueIndex() {
        return constantValueIndex;
    }
}
