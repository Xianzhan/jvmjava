package com.github.xianzhan.jvmjava.java.util;

/**
 * @author xianzhan
 * @since 2020-06-24
 */
public class Symbol {

    // ------ class ------

    public static final String CLASS_OBJ = "java/lang/Object";

    // ------ method ------

    public static final String METHOD_CLINIT = "<clinit>";
    public static final String METHOD_INIT   = "<init>";
    public static final String METHOD_MAIN   = "main";

    // ------ descriptor ------

    public static final String DESCRIPTOR_BOOLEAN = "Z";
    public static final String DESCRIPTOR_BYTE    = "B";
    public static final String DESCRIPTOR_CHAR    = "C";
    public static final String DESCRIPTOR_SHORT   = "S";
    public static final String DESCRIPTOR_INT     = "I";
    public static final String DESCRIPTOR_LONG    = "J";
    public static final String DESCRIPTOR_FLOAT   = "F";
    public static final String DESCRIPTOR_DOUBLE  = "D";
    public static final String DESCRIPTOR_ARR     = "[";
    public static final String DESCRIPTOR_REF     = "L";

    public static final String DESCRIPTOR_STR = "Ljava/lang/String;";

    public static final String DESCRIPTOR_BOOLEAN_V = "(Z)V";
    public static final String DESCRIPTOR_BYTE_V    = "(B)V";
    public static final String DESCRIPTOR_CHAR_V    = "(C)V";
    public static final String DESCRIPTOR_SHORT_V   = "(S)V";
    public static final String DESCRIPTOR_INT_V     = "(I)V";
    public static final String DESCRIPTOR_FLOAT_V   = "(F)V";
    public static final String DESCRIPTOR_LONG_V    = "(J)V";
    public static final String DESCRIPTOR_DOUBLE_V  = "(D)V";
    public static final String DESCRIPTOR_STR_ARR_V = "([Ljava/lang/String;)V";
}
