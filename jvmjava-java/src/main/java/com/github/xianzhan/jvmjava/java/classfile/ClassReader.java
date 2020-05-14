package com.github.xianzhan.jvmjava.java.classfile;

import java.io.DataInputStream;

/**
 * @author xianzhan
 * @since 2020-04-11
 */
public class ClassReader {

    public static ClassFile read(DataInputStream dis) {
        return new ClassFile();
    }
}
