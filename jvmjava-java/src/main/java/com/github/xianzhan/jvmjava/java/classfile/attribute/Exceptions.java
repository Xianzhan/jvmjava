package com.github.xianzhan.jvmjava.java.classfile.attribute;

import com.github.xianzhan.jvmjava.java.classfile.Attribute;

/**
 * Exceptions_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 *     u2 number_of_exceptions;
 *     u2 exception_index_table[number_of_exceptions];
 * }
 * 记录方法抛出的异常表
 *
 * @author xianzhan
 * @since 2020-05-23
 */
public class Exceptions extends Attribute {
}
