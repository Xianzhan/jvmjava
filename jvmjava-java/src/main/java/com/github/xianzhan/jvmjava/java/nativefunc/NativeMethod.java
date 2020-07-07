package com.github.xianzhan.jvmjava.java.nativefunc;

import com.github.xianzhan.jvmjava.java.runtime.Frame;
import com.github.xianzhan.jvmjava.java.util.CollectionUtils;
import com.github.xianzhan.jvmjava.java.util.Symbol;

import java.util.Map;

/**
 * @author xianzhan
 * @since 2020-07-05
 */
public class NativeMethod {

    private static final Map<String, Native> REGISTRY            = CollectionUtils.newHashMap();
    private static final Native              EMPTY_NATIVE_METHOD = frame -> {
    };

    public static void register(String className, String methodName, String methodDescriptor, Native method) {
        var key = genKey(className, methodName, methodDescriptor);
        REGISTRY.put(key, method);
    }

    public static Native findNativeMethod(String className, String methodName, String methodDescriptor) {
        var key = genKey(className, methodName, methodDescriptor);
        var method = REGISTRY.get(key);
        if (method != null) {
            return method;
        }

        if (Symbol.DESCRIPTOR_V_V.equals(methodDescriptor) && Symbol.METHOD_REGISTER_NATIVES.equals(methodName)) {
            return EMPTY_NATIVE_METHOD;
        }
        return null;
    }

    private static String genKey(String className, String methodName, String methodDescriptor) {
        return className + "~" + methodName + "~" + methodDescriptor;
    }

    @FunctionalInterface
    public interface Native {

        /**
         * 本地方法执行
         *
         * @param frame 栈帧
         * @throws Exception 异常
         */
        void exec(Frame frame) throws Exception;
    }
}
