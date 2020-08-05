package xianzhan.jvmjava.java.instruction.references;

import xianzhan.jvmjava.java.classloader.ClassLoader;
import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;
import xianzhan.jvmjava.java.runtime.heap.JClass;
import xianzhan.jvmjava.java.util.Symbol;
import xianzhan.jvmjava.java.util.Types;

/**
 * @author xianzhan
 * @since 2020-07-04
 */
public class NewArrayInst implements Instruction {

    public final int arrayType;

    public NewArrayInst(int arrayType) {
        this.arrayType = arrayType;
    }

    @Override
    public void execute(Frame frame) {
        var stack = frame.operandStack();
        var length = stack.popInt();
        if (length < 0) {
            throw new NegativeArraySizeException();
        }

        var classLoader = frame.method().clazz().loader;
        var arrClass = getPrimitiveArrayClass(classLoader, arrayType);
        var arr = arrClass.newArray(length);
        stack.pushRef(arr);
    }

    private JClass getPrimitiveArrayClass(ClassLoader loader, int arrayType) {
        return switch (arrayType) {
            case Types.ARRAY_TYPE_BOOLEAN -> loader.loadClass(Symbol.DESCRIPTOR_ARR_BOOLEAN);
            case Types.ARRAY_TYPE_BYTE -> loader.loadClass(Symbol.DESCRIPTOR_ARR_BYTE);
            case Types.ARRAY_TYPE_CHAR -> loader.loadClass(Symbol.DESCRIPTOR_ARR_CHAR);
            case Types.ARRAY_TYPE_SHORT -> loader.loadClass(Symbol.DESCRIPTOR_ARR_SHORT);
            case Types.ARRAY_TYPE_INT -> loader.loadClass(Symbol.DESCRIPTOR_ARR_INT);
            case Types.ARRAY_TYPE_LONG -> loader.loadClass(Symbol.DESCRIPTOR_ARR_LONG);
            case Types.ARRAY_TYPE_FLOAT -> loader.loadClass(Symbol.DESCRIPTOR_ARR_FLOAT);
            case Types.ARRAY_TYPE_DOUBLE -> loader.loadClass(Symbol.DESCRIPTOR_ARR_DOUBLE);
            default -> throw new RuntimeException("Invalid array type");
        };
    }

    @Override
    public int offset() {
        return 2;
    }

    @Override
    public String toString() {
        return "newarray " + arrayType;
    }
}
