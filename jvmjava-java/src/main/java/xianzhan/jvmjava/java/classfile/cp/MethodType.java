package xianzhan.jvmjava.java.classfile.cp;

import xianzhan.jvmjava.java.classfile.ConstantInfo;

/**
 * @author xianzhan
 * @since 2020-05-17
 */
public class MethodType extends ConstantInfo {

    public final int descriptorIndex;

    public MethodType(int tag, int descriptorIndex) {
        super(tag);
        this.descriptorIndex = descriptorIndex;
    }
}
