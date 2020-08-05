package xianzhan.jvmjava.java.classfile.cp;

import xianzhan.jvmjava.java.classfile.ConstantInfo;

/**
 * @author xianzhan
 * @since 2020-05-17
 */
public class NameAndType extends ConstantInfo {

    public final int nameIndex;
    public final int descriptionIndex;

    public NameAndType(int tag, int nameIndex, int descriptionIndex) {
        super(tag);
        this.nameIndex = nameIndex;
        this.descriptionIndex = descriptionIndex;
    }

    @Override
    public String toString() {
        return "NameAndType#" + cp().getString(nameIndex) + cp().getString(descriptionIndex);
    }
}
