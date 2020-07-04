package com.github.xianzhan.jvmjava.java.util;

/**
 * @author xianzhan
 * @since 2020-06-24
 */
public interface Symbol {

    // name

    String VOID    = "void";
    String BOOLEAN = "boolean";
    String BYTE    = "byte";
    String SHORT   = "short";
    String CHAR    = "char";
    String INT     = "int";
    String LONG    = "long";
    String FLOAT   = "float";
    String DOUBLE  = "double";

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

    String CLASS_OBJ          = "java/lang/Object";
    String CLASS_CLONEABLE    = "java/lang/Cloneable";
    String CLASS_SERIALIZABLE = "java/io/Serializable";

    // ------ method ------

    String METHOD_CLINIT           = "<clinit>";
    String METHOD_INIT             = "<init>";
    String METHOD_MAIN             = "main";
    String METHOD_REGISTER_NATIVES = "registerNatives";
    String METHOD_PRINTLN          = "println";

    // ------ descriptor ------

    String DESCRIPTOR_BOOLEAN   = "Z";
    String DESCRIPTOR_BYTE      = "B";
    String DESCRIPTOR_CHAR      = "C";
    String DESCRIPTOR_SHORT     = "S";
    String DESCRIPTOR_INT       = "I";
    String DESCRIPTOR_LONG      = "J";
    String DESCRIPTOR_FLOAT     = "F";
    String DESCRIPTOR_DOUBLE    = "D";
    String DESCRIPTOR_ARR       = "[";
    String DESCRIPTOR_REF       = "L";
    String DESCRIPTOR_VOID      = "V";
    String DESCRIPTOR_SEMICOLON = ";";

    String DESCRIPTOR_ARR_BOOLEAN = "[Z";
    String DESCRIPTOR_ARR_BYTE    = "[B";
    String DESCRIPTOR_ARR_CHAR    = "[C";
    String DESCRIPTOR_ARR_SHORT   = "[S";
    String DESCRIPTOR_ARR_INT     = "[I";
    String DESCRIPTOR_ARR_LONG    = "[J";
    String DESCRIPTOR_ARR_FLOAT   = "[F";
    String DESCRIPTOR_ARR_DOUBLE  = "[D";

    String DESCRIPTOR_STR = "Ljava/lang/String;";

    String DESCRIPTOR_V_V       = "()V";
    String DESCRIPTOR_BOOLEAN_V = "(Z)V";
    String DESCRIPTOR_BYTE_V    = "(B)V";
    String DESCRIPTOR_CHAR_V    = "(C)V";
    String DESCRIPTOR_SHORT_V   = "(S)V";
    String DESCRIPTOR_INT_V     = "(I)V";
    String DESCRIPTOR_FLOAT_V   = "(F)V";
    String DESCRIPTOR_LONG_V    = "(J)V";
    String DESCRIPTOR_DOUBLE_V  = "(D)V";
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
}
