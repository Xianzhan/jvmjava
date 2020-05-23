package com.github.xianzhan.jvmjava.java.classfile;

/**
 * @author xianzhan
 * @since 2020-05-18
 */
public class Exception {

    public final int    startPc;
    public final int    endPc;
    public final int    handlerPc;
    public final String clazz;

    public Exception(int startPc, int endPc, int handlerPc, String clazz) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.clazz = clazz;
    }
}
