package xianzhan.jvmjava.java.nativefunc.java.lang;

import xianzhan.jvmjava.java.nativefunc.NativeMethod;
import xianzhan.jvmjava.java.runtime.heap.StringPool;
import xianzhan.jvmjava.java.util.Symbol;

/**
 * @author xianzhan
 * @since 2020-07-06
 */
public class String {

    static {
        // public native String intern();
        NativeMethod.register(Symbol.CLASS_STR, Symbol.METHOD_INTERN, Symbol.DESCRIPTOR_V_STR, frame -> {
            var self = frame.localVars().getThis();
            var interned = StringPool.internString(self);
            frame.operandStack().pushRef(interned);
        });
    }
}
