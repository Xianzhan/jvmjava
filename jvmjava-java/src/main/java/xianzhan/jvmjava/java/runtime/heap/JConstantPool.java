package xianzhan.jvmjava.java.runtime.heap;

import xianzhan.jvmjava.java.classfile.ConstantPool;
import xianzhan.jvmjava.java.classfile.cp.ClassCp;
import xianzhan.jvmjava.java.classfile.cp.DoubleCp;
import xianzhan.jvmjava.java.classfile.cp.FieldDef;
import xianzhan.jvmjava.java.classfile.cp.FloatCp;
import xianzhan.jvmjava.java.classfile.cp.IntegerCp;
import xianzhan.jvmjava.java.classfile.cp.InterfaceMethodDef;
import xianzhan.jvmjava.java.classfile.cp.LongCp;
import xianzhan.jvmjava.java.classfile.cp.MethodDef;
import xianzhan.jvmjava.java.classfile.cp.StringCp;

/**
 * @author xianzhan
 * @since 2020-06-24
 */
public class JConstantPool {

    public        JClass   clazz;
    private final Object[] constArr;

    public JConstantPool(JClass clazz, ConstantPool constantPool) {
        this.clazz = clazz;

        int cpCount = constantPool.infos.length;
        constArr = new Object[cpCount];
        for (int i = 0; i < cpCount; i++) {
            var info = constantPool.infos[i];
            if (info instanceof IntegerCp integerCp) {
                constArr[i] = integerCp.val;
            } else if (info instanceof FloatCp floatCp) {
                constArr[i] = floatCp.val;
            } else if (info instanceof LongCp longCp) {
                constArr[i] = longCp.val;
                i++;
            } else if (info instanceof DoubleCp doubleCp) {
                constArr[i] = doubleCp.val;
                i++;
            } else if (info instanceof StringCp stringCp) {
                constArr[i] = constantPool.getString(stringCp.stringIndex);
            } else if (info instanceof ClassCp classCp) {
                constArr[i] = new CpClassRef(this, classCp);
            } else if (info instanceof FieldDef fieldDef) {
                constArr[i] = new CpFieldRef(this, fieldDef);
            } else if (info instanceof MethodDef methodDef) {
                constArr[i] = new CpMethodRef(this, methodDef);
            } else if (info instanceof InterfaceMethodDef interfaceMethodDef) {
                constArr[i] = new CpInterfaceMethodRef(this, interfaceMethodDef);
            }
        }
    }

    public Object getConstant(int index) {
        var constant = constArr[index - 1];
        if (constant == null) {
            System.err.println("No constants at index " + index);
        }
        return constant;
    }
}
