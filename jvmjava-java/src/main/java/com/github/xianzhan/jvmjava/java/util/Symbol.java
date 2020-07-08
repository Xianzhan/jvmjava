package com.github.xianzhan.jvmjava.java.util;

/**
 * @author xianzhan
 * @since 2020-06-24
 */
public interface Symbol {

    // name

    String BOOLEAN = "boolean";
    String BYTE    = "byte";
    String CHAR    = "char";
    String DOUBLE  = "double";
    String FLOAT   = "float";
    String INT     = "int";
    String LONG    = "long";
    String SHORT   = "short";
    String VOID    = "void";

    /**
     * 由原始类型获取描述符
     * <p>
     * int -> I
     *
     * @param primitiveType 原始类型
     * @return 描述符
     */
    static String primitiveDescriptor(String primitiveType) {
        return switch (primitiveType) {
            case DESCRIPTOR_VOID -> VOID;
            case DESCRIPTOR_BOOLEAN -> BOOLEAN;
            case DESCRIPTOR_BYTE -> BYTE;
            case DESCRIPTOR_SHORT -> SHORT;
            case DESCRIPTOR_INT -> INT;
            case DESCRIPTOR_CHAR -> CHAR;
            case DESCRIPTOR_LONG -> LONG;
            case DESCRIPTOR_FLOAT -> FLOAT;
            case DESCRIPTOR_DOUBLE -> DOUBLE;
            default -> "";
        };
    }

    // ------ class ------

    String CLASS_SERIALIZABLE         = "java/io/Serializable";
    String CLASS_CLASS                = "java/lang/Class";
    String CLASS_CLONEABLE            = "java/lang/Cloneable";
    String CLASS_DOUBLE               = "java/lang/Double";
    String CLASS_FLOAT                = "java/lang/Float";
    String CLASS_OBJ                  = "java/lang/Object";
    String CLASS_STR                  = "java/lang/String";
    String CLASS_SYSTEM               = "java/lang/System";
    String CLASS_THROWABLE            = "java/lang/Throwable";
    String CLASS_JAVA_UTIL_PROPERTIES = "java/util/Properties";
    String CLASS_SUN_MISC_VM          = "sun/misc/VM";

    // ------ method ------

    String METHOD_CLINIT                     = "<clinit>";
    String METHOD_INIT                       = "<init>";
    String METHOD_ARRAY_COPY                 = "arraycopy";
    String METHOD_CLONE                      = "clone";
    String METHOD_DESIRED_ASSERTION_STATUS_0 = "desiredAssertionStatus0";
    String METHOD_DOUBLE_TO_RAW_LONG_BITS    = "doubleToRawLongBits";
    String METHOD_FLOAT_TO_RAW_INT_BITS      = "floatToRawIntBits";
    String METHOD_FILL_IN_STACK_TRACE        = "fillInStackTrace";
    String METHOD_GET_CLASS                  = "getClass";
    String METHOD_GET_NAME_0                 = "getName0";
    String METHOD_GET_PRIMITIVE_CLASS        = "getPrimitiveClass";
    String METHOD_HASH_CODE                  = "hashCode";
    String METHOD_INITIALIZE                 = "initialize";
    String METHOD_INT_BITS_TO_FLOAT          = "intBitsToFloat";
    String METHOD_INTERN                     = "intern";
    String METHOD_LONG_BITS_TO_DOUBLE        = "longBitsToDouble";
    String METHOD_MAIN                       = "main";
    String METHOD_PRINTLN                    = "println";
    String METHOD_REGISTER_NATIVES           = "registerNatives";
    String METHOD_SET_PROPERTIES             = "setProperty";

    // ------ descriptor ------

    String DESCRIPTOR_SEMICOLON = ";";
    String DESCRIPTOR_BYTE      = "B";
    String DESCRIPTOR_CHAR      = "C";
    String DESCRIPTOR_DOUBLE    = "D";
    String DESCRIPTOR_FLOAT     = "F";
    String DESCRIPTOR_INT       = "I";
    String DESCRIPTOR_LONG      = "J";
    String DESCRIPTOR_REF       = "L";
    String DESCRIPTOR_SHORT     = "S";
    String DESCRIPTOR_VOID      = "V";
    String DESCRIPTOR_BOOLEAN   = "Z";
    String DESCRIPTOR_ARR       = "[";

    String DESCRIPTOR_ARR_BYTE    = "[B";
    String DESCRIPTOR_ARR_CHAR    = "[C";
    String DESCRIPTOR_ARR_DOUBLE  = "[D";
    String DESCRIPTOR_ARR_FLOAT   = "[F";
    String DESCRIPTOR_ARR_INT     = "[I";
    String DESCRIPTOR_ARR_LONG    = "[J";
    String DESCRIPTOR_ARR_SHORT   = "[S";
    String DESCRIPTOR_ARR_BOOLEAN = "[Z";

    String DESCRIPTOR_STR                  = "Ljava/lang/String;";
    String DESCRIPTOR_JAVA_UTIL_PROPERTIES = "Ljava/util/Properties;";

    String DESCRIPTOR_V_INT   = "()I";
    String DESCRIPTOR_V_CLASS = "()Ljava/lang/Class;";
    String DESCRIPTOR_V_OBJ   = "()Ljava/lang/Object;";
    String DESCRIPTOR_V_STR   = "()Ljava/lang/String;";
    String DESCRIPTOR_V_V     = "()V";

    String DESCRIPTOR_BYTE_V        = "(B)V";
    String DESCRIPTOR_CHAR_V        = "(C)V";
    String DESCRIPTOR_DOUBLE_LONG   = "(D)J";
    String DESCRIPTOR_DOUBLE_V      = "(D)V";
    String DESCRIPTOR_FLOAT_INT     = "(F)I";
    String DESCRIPTOR_FLOAT_V       = "(F)V";
    String DESCRIPTOR_INT_FLOAT     = "(I)F";
    String DESCRIPTOR_INT_THROWABLE = "(I)Ljava/lang/Throwable;";
    String DESCRIPTOR_INT_V         = "(I)V";
    String DESCRIPTOR_LONG_DOUBLE   = "(J)D";
    String DESCRIPTOR_LONG_V        = "(J)V";
    String DESCRIPTOR_SHORT_V       = "(S)V";
    String DESCRIPTOR_BOOLEAN_V     = "(Z)V";

    String DESCRIPTOR_CLASS_BOOLEAN         = "(Ljava/lang/Class;)Z";
    String DESCRIPTOR_OBJ_INT_OBJ_INT_INT_V = "(Ljava/lang/Object;ILjava/lang/Object;II)V";
    String DESCRIPTOR_STR_CLASS             = "(Ljava/lang/String;)Ljava/lang/Class;";
    String DESCRIPTOR_STR_V                 = "(Ljava/lang/String;)V";
    String DESCRIPTOR_STR_STR_OBJ           = "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;";

    String DESCRIPTOR_STR_ARR_V = "([Ljava/lang/String;)V";

    /**
     * 由描述符获取原始类型
     * <p>
     * I -> int
     *
     * @param descriptor 描述符
     * @return 原始类型
     */
    static String descriptorPrimitive(String descriptor) {
        return switch (descriptor) {
            case VOID -> DESCRIPTOR_VOID;
            case BOOLEAN -> DESCRIPTOR_BOOLEAN;
            case BYTE -> DESCRIPTOR_BYTE;
            case SHORT -> DESCRIPTOR_SHORT;
            case INT -> DESCRIPTOR_INT;
            case CHAR -> DESCRIPTOR_CHAR;
            case LONG -> DESCRIPTOR_LONG;
            case FLOAT -> DESCRIPTOR_FLOAT;
            case DOUBLE -> DESCRIPTOR_DOUBLE;
            default -> "";
        };
    }

    // field

    String FIELD_DETAIL_MESSAGE = "detailMessage";
    String FIELD_SAVED_PROPS    = "savedProps";
    String FIELD_VALUE          = "value";
}
