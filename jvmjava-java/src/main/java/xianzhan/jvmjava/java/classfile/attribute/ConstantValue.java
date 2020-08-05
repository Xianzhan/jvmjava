package xianzhan.jvmjava.java.classfile.attribute;

import xianzhan.jvmjava.java.classfile.Attribute;
import xianzhan.jvmjava.java.classfile.ConstantInfo;

/**
 * ConstantValue_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 *     u2 constantvalue_index;
 * }
 *
 * @author xianzhan
 * @see ConstantInfo#CONSTANT_Integer
 * @see ConstantInfo#CONSTANT_Long
 * @see ConstantInfo#CONSTANT_Float
 * @see ConstantInfo#CONSTANT_Double
 * @see ConstantInfo#CONSTANT_String
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
