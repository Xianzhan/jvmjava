package com.github.xianzhan.jvmjava.java.nativefunc.java.lang;

import com.github.xianzhan.jvmjava.java.nativefunc.NativeMethod;
import com.github.xianzhan.jvmjava.java.runtime.heap.JObject;
import com.github.xianzhan.jvmjava.java.util.Symbol;

/**
 * @author xianzhan
 * @since 2020-07-06
 */
public class System {

    static {
        NativeMethod.register(Symbol.CLASS_SYSTEM, Symbol.METHOD_ARRAY_COPY, Symbol.DESCRIPTOR_OBJ_INT_OBJ_INT_INT_V, frame -> {
            var vars = frame.localVars();
            var src = vars.getRef(0);
            var srcPos = vars.getInt(1);
            var dest = vars.getRef(2);
            var destPos = vars.getInt(3);
            var length = vars.getInt(4);

            if (src == null || dest == null) {
                throw new NullPointerException();
            }
            if (!checkArrayCopy(src, dest)) {
                throw new ArrayStoreException();
            }

            if (srcPos < 0 || destPos < 0 || length < 0 ||
                srcPos + length > src.arrayLength() ||
                destPos + length > dest.arrayLength()) {
                throw new IndexOutOfBoundsException();
            }

            java.lang.System.arraycopy(src.data(), srcPos, dest.data(), destPos, length);
        });
    }

    private static boolean checkArrayCopy(JObject src, JObject dest) {
        var srcClass = src.clazz();
        var destClass = dest.clazz();

        if (!srcClass.isArray() || !destClass.isArray()) {
            return false;
        }

        if (srcClass.componentClass().isPrimitive() ||
            destClass.componentClass().isPrimitive()) {
            // 原始类型
            return srcClass == destClass;
        }

        return true;
    }
}
