package xianzhan.jvmjava.java.instruction.references;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;
import xianzhan.jvmjava.java.runtime.OperandStack;
import xianzhan.jvmjava.java.runtime.heap.CpClassRef;
import xianzhan.jvmjava.java.runtime.heap.JClass;
import xianzhan.jvmjava.java.runtime.heap.JObject;

import java.util.Arrays;

/**
 * Create new multidimensional array
 *
 * @author xianzhan
 * @since 2020-07-04
 */
public class MultiANewArrayInst implements Instruction {

    public final int index;
    public final int dimensions;

    public MultiANewArrayInst(int index, int dimensions) {
        this.index = index;
        this.dimensions = dimensions;
    }

    @Override
    public void execute(Frame frame) {
        var cp = frame.method().clazz().constantPool();
        var classRef = (CpClassRef) cp.getConstant(index);
        var arrClass = classRef.resolvedClass();

        var stack = frame.operandStack();
        var lengths = popAndCheckLengths(stack, dimensions);
        var arr = newMultiDimensionalArray(lengths, arrClass);
        stack.pushRef(arr);
    }

    private JObject newMultiDimensionalArray(int[] lengths, JClass arrClass) {
        var length = lengths[0];
        var arr = arrClass.newArray(length);

        var len = lengths.length;
        if (len > 1) {
            var refs = arr.refs();
            for (int i = 0; i < refs.length; i++) {
                refs[i] = newMultiDimensionalArray(
                        Arrays.copyOfRange(lengths, 1, len),
                        arrClass.componentClass()
                );
            }
        }
        return arr;
    }

    private int[] popAndCheckLengths(OperandStack stack, int dimensions) {
        var lengths = new int[dimensions];
        for (int i = dimensions - 1; i >= 0; i--) {
            var length = stack.popInt();
            if (length < 0) {
                throw new NegativeArraySizeException();
            }
            lengths[i] = length;
        }
        return lengths;
    }

    @Override
    public int offset() {
        return 4;
    }

    @Override
    public String toString() {
        return "multianewarray";
    }
}
