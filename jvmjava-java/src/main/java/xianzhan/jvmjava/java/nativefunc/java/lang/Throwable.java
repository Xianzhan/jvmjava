package xianzhan.jvmjava.java.nativefunc.java.lang;

import xianzhan.jvmjava.java.nativefunc.NativeMethod;
import xianzhan.jvmjava.java.runtime.Frame;
import xianzhan.jvmjava.java.runtime.JThread;
import xianzhan.jvmjava.java.runtime.heap.JClass;
import xianzhan.jvmjava.java.runtime.heap.JObject;
import xianzhan.jvmjava.java.util.Symbol;

/**
 * @author xianzhan
 * @since 2020-07-08
 */
public class Throwable {

    static {
        // private native Throwable fillInStackTrace(int dummy);
        NativeMethod.register(Symbol.CLASS_THROWABLE, Symbol.METHOD_FILL_IN_STACK_TRACE, Symbol.DESCRIPTOR_INT_THROWABLE, frame -> {
            var self = frame.localVars().getThis();
            var stack = frame.operandStack();
            stack.pushRef(self);

            var steArr = createStackTraceElements(self, frame.thread());
            self.extra(steArr);
        });
    }

    private static StackTraceElement[] createStackTraceElements(JObject self, JThread thread) {
        var skip = distanceToObject(self.clazz()) + 2;
        var frames = thread.getFrames();
        var length = frames.size();
        var subFrames = frames.subList(skip, length);

        var steArr = new StackTraceElement[subFrames.size()];
        for (int i = 0; i < steArr.length; i++) {
            var frame = subFrames.get(i);
            steArr[i] = createStackTraceElement(frame);
        }
        return steArr;
    }

    private static StackTraceElement createStackTraceElement(Frame frame) {
        var method = frame.method();
        var clazz = method.clazz();
        var pc = frame.nextPc() - 1;
        return new StackTraceElement(
                clazz.sourceFile(),
                clazz.javaName(),
                method.name(),
                method.getLineNumber(pc)
        );
    }

    private static int distanceToObject(JClass clazz) {
        var distance = 0;
        for (var c = clazz.superClass; c != null; c = c.superClass) {
            distance++;
        }
        return distance;
    }

    public static class StackTraceElement {
        public final java.lang.String fileName;
        public final java.lang.String className;
        public final java.lang.String methodName;
        public final int              lineNumber;

        public StackTraceElement(java.lang.String fileName,
                                 java.lang.String className,
                                 java.lang.String methodName,
                                 int lineNumber) {
            this.fileName = fileName;
            this.className = className;
            this.methodName = methodName;
            this.lineNumber = lineNumber;
        }

        @Override
        public java.lang.String toString() {
            return className + "." + methodName + "(" + fileName + ":" + lineNumber + ")";
        }
    }
}
