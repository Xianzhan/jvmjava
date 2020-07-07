package com.github.xianzhan.jvmjava.java.nativefunc.java.lang;

import com.github.xianzhan.jvmjava.java.nativefunc.NativeMethod;
import com.github.xianzhan.jvmjava.java.runtime.heap.JClass;
import com.github.xianzhan.jvmjava.java.runtime.heap.StringPool;
import com.github.xianzhan.jvmjava.java.util.Symbol;

/**
 * @author xianzhan
 * @since 2020-07-05
 */
public class Class {

    static {
        // java.lang.Class#getPrimitiveClass(String)
        NativeMethod.register(Symbol.CLASS_CLASS, Symbol.METHOD_GET_PRIMITIVE_CLASS, Symbol.DESCRIPTOR_STR_CLASS, frame -> {
            var nameObj = frame.localVars().getRef(0);
            var name = StringPool.string(nameObj);

            var loader = frame.method().clazz().loader;
            var clazz = loader.loadClass(name).jClass();

            frame.operandStack().pushRef(clazz);
        });

        // java.lang.Class#getName0()
        NativeMethod.register(Symbol.CLASS_CLASS, Symbol.METHOD_GET_NAME_0, Symbol.DESCRIPTOR_V_STR, frame -> {
            var self = frame.localVars().getThis();
            var clazz = (JClass) self.extra();

            var name = clazz.javaName();
            var nameObj = StringPool.jString(clazz.loader, name);

            frame.operandStack().pushRef(nameObj);
        });

        // java.lang.Class#desiredAssertionStatus0(Class)
        NativeMethod.register(Symbol.CLASS_CLASS, Symbol.METHOD_DESIRED_ASSERTION_STATUS_0, Symbol.DESCRIPTOR_CLASS_BOOLEAN, frame -> {
            // todo
            frame.operandStack().pushBoolean(false);
        });

        // todo
        // isInterface
        // isPrimitive
    }
}
