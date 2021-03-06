package xianzhan.jvmjava.java.classfile;

import xianzhan.jvmjava.java.classfile.attribute.Code;
import xianzhan.jvmjava.java.classfile.attribute.LineNumberTable;
import xianzhan.jvmjava.java.util.AccessFlags;

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
public class Method extends Member {

    public Method(int accessFlags,
                  String name,
                  Descriptor descriptor,
                  Attributes attributes) {
        super(accessFlags, name, descriptor, attributes);
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

    public boolean isStatic() {
        return (accessFlags & AccessFlags.ACC_STATIC) != 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
