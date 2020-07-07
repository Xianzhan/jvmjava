package com.github.xianzhan.jvmjava.java.nativefunc.java.lang;

import com.github.xianzhan.jvmjava.java.nativefunc.NativeMethod;
import com.github.xianzhan.jvmjava.java.util.Symbol;

/**
 * @author xianzhan
 * @since 2020-07-05
 */
public class Object {

    static {
        /*
         * java.lang.Object#getClass()
         */
        NativeMethod.register(Symbol.CLASS_OBJ, Symbol.METHOD_GET_CLASS, Symbol.DESCRIPTOR_V_CLASS, frame -> {
            var self = frame.localVars().getThis();
            var clazz = self.clazz().jClass();
            frame.operandStack().pushRef(clazz);
        });

        /*
         * java.lang.Object#hashCode()
         */
        NativeMethod.register(Symbol.CLASS_OBJ, Symbol.METHOD_HASH_CODE, Symbol.DESCRIPTOR_V_INT, frame -> {
            var self = frame.localVars().getThis();
            var hashCode = self.hashCode();
            frame.operandStack().pushInt(hashCode);
        });

        /*
         * java.lang.Object#clone()
         */
        NativeMethod.register(Symbol.CLASS_OBJ, Symbol.METHOD_CLONE, Symbol.DESCRIPTOR_V_OBJ, frame -> {
            var self = frame.localVars().getThis();

            var cloneable = self.clazz().loader.loadClass(Symbol.CLASS_CLONEABLE);
            if (!self.clazz().isImplements(cloneable)) {
                throw new CloneNotSupportedException();
            }

            var clone = self.clone();
            frame.operandStack().pushRef(clone);
        });
    }
}
