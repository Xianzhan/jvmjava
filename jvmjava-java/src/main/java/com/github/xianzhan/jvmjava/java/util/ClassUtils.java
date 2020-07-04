package com.github.xianzhan.jvmjava.java.util;

/**
 * @author xianzhan
 * @since 2020-07-03
 */
public class ClassUtils {

    /**
     * [[xxx -> [xxx
     * [Lxx; -> xx
     * [I -> int
     *
     * @param className 类描述符
     * @return 类名
     */
    public static String getComponentClassName(String className) {
        if (!className.startsWith(Symbol.DESCRIPTOR_ARR)) {
            throw new RuntimeException("Not array: " + className);
        }

        var componentTypeDescriptor = className.substring(1);
        return toClassName(componentTypeDescriptor);
    }

    /**
     * [xxx -> [[xxx
     * int -> [I
     * xx -> [Lxx;
     *
     * @param className 类名
     * @return 类数组
     */
    public static String getArrayClassName(String className) {
        return Symbol.DESCRIPTOR_ARR + toDescriptor(className);
    }

    /**
     * [xxx -> [xxx
     * int -> I
     * xx -> Lxx;
     *
     * @param className 类名
     * @return 描述符
     */
    private static String toDescriptor(String className) {
        if (className.startsWith(Symbol.DESCRIPTOR_ARR)) {
            // array
            return className;
        }
        var primitive = Symbol.descriptorPrimitive(className);
        if (primitive.isEmpty()) {
            // object
            return Symbol.DESCRIPTOR_REF + className + Symbol.DESCRIPTOR_SEMICOLON;
        }

        // primitive
        return primitive;
    }

    /**
     * [xxx -> [xxx
     * Lxx; -> xx
     * I -> int
     *
     * @param descriptor 描述符
     * @return 类名
     */
    private static String toClassName(String descriptor) {
        if (descriptor.startsWith(Symbol.DESCRIPTOR_ARR)) {
            // array
            return descriptor;
        }

        if (descriptor.startsWith(Symbol.DESCRIPTOR_REF)) {
            // object
            var len = descriptor.length();
            return descriptor.substring(1, len - 1);
        }

        var className = Symbol.primitiveDescriptor(descriptor);
        if (className.isEmpty()) {
            throw new RuntimeException("Invalid descriptor: " + descriptor);
        }
        return className;
    }
}
