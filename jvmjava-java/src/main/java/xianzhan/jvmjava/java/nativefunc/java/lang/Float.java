package xianzhan.jvmjava.java.nativefunc.java.lang;

import xianzhan.jvmjava.java.nativefunc.NativeMethod;
import xianzhan.jvmjava.java.util.Symbol;

/**
 * @author xianzhan
 * @since 2020-07-06
 */
public class Float {
    static {
        // public static native int floatToRawIntBits(float value);
        NativeMethod.register(Symbol.CLASS_FLOAT, Symbol.METHOD_FLOAT_TO_RAW_INT_BITS, Symbol.DESCRIPTOR_FLOAT_INT, frame -> {
            var value = frame.localVars().getFloat(0);
            var bits = java.lang.Float.floatToRawIntBits(value);
            frame.operandStack().pushInt(bits);
        });

        // public static native float intBitsToFloat(int bits);
        NativeMethod.register(Symbol.CLASS_FLOAT, Symbol.METHOD_INT_BITS_TO_FLOAT, Symbol.DESCRIPTOR_INT_FLOAT, frame -> {
            var bits = frame.localVars().getInt(0);
            var value = java.lang.Float.intBitsToFloat(bits);
            frame.operandStack().pushFloat(value);
        });
    }
}
