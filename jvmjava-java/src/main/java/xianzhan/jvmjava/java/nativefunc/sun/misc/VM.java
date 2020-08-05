package xianzhan.jvmjava.java.nativefunc.sun.misc;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.nativefunc.NativeMethod;
import xianzhan.jvmjava.java.runtime.heap.StringPool;
import xianzhan.jvmjava.java.util.Symbol;

/**
 * @author xianzhan
 * @since 2020-07-06
 */
public class VM {

    static {
        NativeMethod.register(Symbol.CLASS_SUN_MISC_VM, Symbol.METHOD_INITIALIZE, Symbol.DESCRIPTOR_V_V, frame -> {
            var vmClass = frame.method().clazz();
            var savedProps = vmClass.getRefVar(Symbol.FIELD_SAVED_PROPS, Symbol.DESCRIPTOR_JAVA_UTIL_PROPERTIES);
            var key = StringPool.jString(vmClass.loader, "foo");
            var val = StringPool.jString(vmClass.loader, "bar");

            frame.operandStack().pushRef(savedProps);
            frame.operandStack().pushRef(key);
            frame.operandStack().pushRef(val);

            var propsClass = vmClass.loader.loadClass(Symbol.CLASS_JAVA_UTIL_PROPERTIES);
            var setProperties = propsClass.getInstanceMethod(Symbol.METHOD_SET_PROPERTIES, Symbol.DESCRIPTOR_STR_STR_OBJ);
            Instruction.invokeMethod(frame, setProperties);
        });
    }
}
