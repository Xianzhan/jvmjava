package com.github.xianzhan.jvmjava.java.classfile;

import com.github.xianzhan.jvmjava.java.classfile.attribute.Code;
import com.github.xianzhan.jvmjava.java.classfile.attribute.LineNumberTable;

/**
 * method_info {
 *     u2             access_flags;
 *     u2             name_index;
 *     u2             descriptor_index;
 *     u2             attributes_count;
 *     attribute_info attributes[attributes_count];
 * }
 *
 * @author xianzhan
 * @since 2020-05-18
 */
public class Method {

    public final int        accessFlags;
    public final String     name;
    public final Descriptor descriptor;
    public final Attributes attributes;

    public Method(int accessFlags, String name, Descriptor descriptor, Attributes attributes) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.descriptor = descriptor;
        this.attributes = attributes;
    }

    public Code getCode() {
        for (Attribute attribute : attributes.attributes) {
            if (attribute instanceof Code) {
                return ((Code) attribute);
            }
        }
        return null;
    }

    public LineNumberTable getLineNumber() {
        if (this.getCode() == null) {
            return null;
        }
        for (Attribute attribute : this.getCode().attributes.attributes) {
            if (attribute instanceof LineNumberTable) {
                return ((LineNumberTable) attribute);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
