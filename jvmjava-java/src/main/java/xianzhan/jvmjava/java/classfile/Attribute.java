package xianzhan.jvmjava.java.classfile;

/**
 * @author xianzhan
 * @since 2020-05-18
 */
public class Attribute {

    // ==============================
    // 实现 Java 虚拟机所必需的, 共 5 种
    // ==============================

    /**
     * field_info
     *
     * @since 1.0.2
     */
    public static final String ConstantValue    = "ConstantValue";
    /**
     * method_info
     *
     * @since 1.0.2
     */
    public static final String Code             = "Code";
    /**
     * Code
     *
     * @since 6
     */
    public static final String StackMapTable    = "StackMapTable";
    /**
     * method_info
     *
     * @since 1.0.2
     */
    public static final String Exceptions       = "Exceptions";
    /**
     * ClassFile
     *
     * @since 7
     */
    public static final String BootstrapMethods = "BootstrapMethods";

    // =========================
    // Java 类库所必需的, 共 12 种
    // =========================

    /**
     * ClassFile
     *
     * @since 1.1
     */
    public static final String InnerClasses                         = "InnerClasses";
    /**
     * ClassFile
     *
     * @since 5
     */
    public static final String EnclosingMethod                      = "EnclosingMethod";
    /**
     * ClassFile, field_info, method_info
     *
     * @since 1.1
     */
    public static final String Synthetic                            = "Synthetic";
    /**
     * ClassFile, field_info, method_info
     *
     * @since 5
     */
    public static final String Signature                            = "Signature";
    /**
     * ClassFile, field_info, method_info
     *
     * @since 5
     */
    public static final String RuntimeVisibleAnnotations            = "RuntimeVisibleAnnotations";
    /**
     * ClassFile, field_info, method_info
     *
     * @since 5
     */
    public static final String RuntimeInvisibleAnnotations          = "RuntimeInvisibleAnnotations";
    /**
     * method_info
     *
     * @since 5
     */
    public static final String RuntimeVisibleParameterAnnotations   = "RuntimeVisibleParameterAnnotations";
    /**
     * method_info
     *
     * @since 5
     */
    public static final String RuntimeInvisibleParameterAnnotations = "RuntimeInvisibleParameterAnnotations";
    /**
     * ClassFile, field_info, method_info, Code
     *
     * @since 8
     */
    public static final String RuntimeVisibleTypeAnnotations        = "RuntimeVisibleTypeAnnotations";
    /**
     * ClassFile, field_info, method_info, Code
     *
     * @since 8
     */
    public static final String RuntimeInvisibleTypeAnnotations      = "RuntimeInvisibleTypeAnnotations";
    /**
     * method_info
     *
     * @since 5
     */
    public static final String AnnotationDefault                    = "AnnotationDefault";
    /**
     * method_info
     *
     * @since 8
     */
    public static final String MethodParameters                     = "MethodParameters";

    // =======================
    // 主要供给工具使用, 共 6 种
    // =======================

    /**
     * ClassFile
     *
     * @since 1.0.2
     */
    public static final String SourceFile             = "SourceFile";
    /**
     * ClassFile
     *
     * @since 5
     */
    public static final String SourceDebugExtension   = "SourceDebugExtension";
    /**
     * Code
     *
     * @since 1.0.2
     */
    public static final String LineNumberTable        = "LineNumberTable";
    /**
     * Code
     *
     * @since 1.0.2
     */
    public static final String LocalVariableTable     = "LocalVariableTable";
    /**
     * Code
     *
     * @since 5
     */
    public static final String LocalVariableTypeTable = "LocalVariableTypeTable";
    /***
     * ClassFile, field_info, method_info
     * @since 1.1
     */
    public static final String Deprecated             = "Deprecated";

}
