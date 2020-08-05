package xianzhan.jvmjava.java.classfile;

/**
 * field_info {
 *     u2             access_flags;
 *     u2             name_index;
 *     u2             descriptor_index;
 *     u2             attributes_count;
 *     attribute_info attributes[attributes_count];
 * }
 * @author xianzhan
 * @since 2020-05-18
 */
public class Field extends Member {

    public Field(int accessFlags,
                 String name,
                 Descriptor descriptor,
                 Attributes attributes) {
        super(accessFlags, name, descriptor, attributes);
    }

    @Override
    public String toString() {
        return name + " " + descriptor;
    }
}
