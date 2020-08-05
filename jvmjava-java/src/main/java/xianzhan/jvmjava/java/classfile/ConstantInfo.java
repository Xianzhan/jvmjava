package xianzhan.jvmjava.java.classfile;

/**
 * @author xianzhan
 * @since 2020-05-17
 */
public class ConstantInfo {

    public static final int CONSTANT_Class              = 7;
    public static final int CONSTANT_Fieldref           = 9;
    public static final int CONSTANT_Methodref          = 10;
    public static final int CONSTANT_InterfaceMethodref = 11;
    public static final int CONSTANT_String             = 8;
    public static final int CONSTANT_Integer            = 3;
    public static final int CONSTANT_Float              = 4;
    public static final int CONSTANT_Long               = 5;
    public static final int CONSTANT_Double             = 6;
    public static final int CONSTANT_NameAndType        = 12;
    public static final int CONSTANT_Utf8               = 1;
    public static final int CONSTANT_MethodHandle       = 15;
    public static final int CONSTANT_MethodType         = 16;
    public static final int CONSTANT_InvokeDynamic      = 18;

    /**
     * Constant pool tags
     */
    public final int tag;

    private ConstantPool cp;

    public ConstantInfo(int tag) {
        this.tag = tag;
    }

    public void cp(ConstantPool cp) {
        this.cp = cp;
    }

    public ConstantPool cp() {
        return cp;
    }
}
