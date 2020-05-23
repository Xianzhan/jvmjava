package com.github.xianzhan.jvmjava.java.classfile.attribute;

import com.github.xianzhan.jvmjava.java.classfile.Attribute;

/**
 * SourceFile_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 *     u2 sourcefile_index;
 * }
 * @author xianzhan
 * @since 2020-05-18
 */
public class SourceFile extends Attribute {

    public final String name;

    public SourceFile(String name) {
        this.name = name;
    }
}
