package xianzhan.jvmjava.java.classfile.cp;

/**
 * @author xianzhan
 * @since 2020-05-17
 */
public class MethodDef extends MemberInfo {

    public MethodDef(int tag, int classIndex, int nameAndTypeIndex) {
        super(tag, classIndex, nameAndTypeIndex);
    }

    @Override
    public String toString() {
        return "MethodDef#" + className() + "." + name() + descriptor();
    }
}