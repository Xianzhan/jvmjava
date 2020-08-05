package xianzhan.jvmjava.java.classfile;

import xianzhan.jvmjava.java.classfile.attribute.Code;
import xianzhan.jvmjava.java.classfile.attribute.ConstantValue;

/**
 * 成员
 *
 * @author xianzhan
 * @since 2020-06-24
 */
public class Member {

    public final int        accessFlags;
    public final String     name;
    public final Descriptor descriptor;
    public final Attributes attributes;

    public Member(int accessFlags, String name, Descriptor descriptor, Attributes attributes) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.descriptor = descriptor;
        this.attributes = attributes;
    }

    public int accessFlags() {
        return accessFlags;
    }

    public String name() {
        return name;
    }

    public String descriptor() {
        return descriptor.descriptor;
    }

    public Code codeAttribute() {
        for (var attr : attributes.attributes) {
            if (attr instanceof Code code) {
                return code;
            }
        }
        return null;
    }

    public ConstantValue constantValueAttribute() {
        for (var attr : attributes.attributes) {
            if (attr instanceof ConstantValue constantValue) {
                return constantValue;
            }
        }
        return null;
    }
}
