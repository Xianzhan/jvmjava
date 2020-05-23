package com.github.xianzhan.jvmjava.java.classfile;

import java.util.Arrays;

/**
 * @author xianzhan
 * @since 2020-05-17
 */
public class Interfaces {

    public final Interface[] interfaces;

    public Interfaces(Interface[] interfaces) {
        this.interfaces = interfaces;
    }

    @Override
    public String toString() {
        return Arrays.toString(interfaces);
    }
}
