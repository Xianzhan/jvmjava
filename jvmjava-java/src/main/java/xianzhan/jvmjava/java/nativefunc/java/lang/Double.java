package xianzhan.jvmjava.java.nativefunc.java.lang;

import xianzhan.jvmjava.java.nativefunc.NativeMethod;
import xianzhan.jvmjava.java.util.Symbol;

/**
 * @author xianzhan
 * @since 2020-07-05
 */
public class Double {

    static {
        // public static native long doubleToRawLongBits(double value);
        NativeMethod.register(Symbol.CLASS_DOUBLE, Symbol.METHOD_DOUBLE_TO_RAW_LONG_BITS, Symbol.DESCRIPTOR_DOUBLE_LONG, frame -> {
            var value = frame.localVars().getDouble(0);
            var bits = java.lang.Double.doubleToRawLongBits(value);
            frame.operandStack().pushLong(bits);
        });

        // public static native double longBitsToDouble(long bits);
        NativeMethod.register(Symbol.CLASS_DOUBLE, Symbol.METHOD_LONG_BITS_TO_DOUBLE, Symbol.DESCRIPTOR_LONG_DOUBLE, frame -> {
            var bits = frame.localVars().getLong(0);
            var value = java.lang.Double.longBitsToDouble(bits);
            frame.operandStack().pushDouble(value);
        });
    }
}
