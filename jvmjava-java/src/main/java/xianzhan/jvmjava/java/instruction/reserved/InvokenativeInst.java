package xianzhan.jvmjava.java.instruction.reserved;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.nativefunc.NativeMethod;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * @author xianzhan
 * @since 2020-07-05
 */
public class InvokenativeInst implements Instruction {

    static {
        // Register Native Method
        try {
            // java.lang
            Class.forName("xianzhan.jvmjava.java.nativefunc.java.lang.Class");
            Class.forName("xianzhan.jvmjava.java.nativefunc.java.lang.Double");
            Class.forName("xianzhan.jvmjava.java.nativefunc.java.lang.Float");
            Class.forName("xianzhan.jvmjava.java.nativefunc.java.lang.Object");
            Class.forName("xianzhan.jvmjava.java.nativefunc.java.lang.String");
            Class.forName("xianzhan.jvmjava.java.nativefunc.java.lang.System");
            Class.forName("xianzhan.jvmjava.java.nativefunc.java.lang.Throwable");

            // sun.misc
            Class.forName("xianzhan.jvmjava.java.nativefunc.sun.misc.VM");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void execute(Frame frame) {
        var method = frame.method();
        var className = method.clazz().name;
        var methodName = method.name();
        var methodDescriptor = method.descriptor();

        var nativeMethod = NativeMethod.findNativeMethod(className, methodName, methodDescriptor);
        if (nativeMethod == null) {
            var methodInfo = className + "." + methodName + methodDescriptor;
            throw new UnsatisfiedLinkError(methodInfo);
        }

        try {
            nativeMethod.exec(frame);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "invokenative";
    }
}
