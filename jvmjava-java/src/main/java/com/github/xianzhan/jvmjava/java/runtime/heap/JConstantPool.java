package com.github.xianzhan.jvmjava.java.runtime.heap;

import com.github.xianzhan.jvmjava.java.classfile.ConstantPool;
import com.github.xianzhan.jvmjava.java.classfile.cp.ClassCp;
import com.github.xianzhan.jvmjava.java.classfile.cp.DoubleCp;
import com.github.xianzhan.jvmjava.java.classfile.cp.FieldDef;
import com.github.xianzhan.jvmjava.java.classfile.cp.FloatCp;
import com.github.xianzhan.jvmjava.java.classfile.cp.IntegerCp;
import com.github.xianzhan.jvmjava.java.classfile.cp.InterfaceMethodDef;
import com.github.xianzhan.jvmjava.java.classfile.cp.LongCp;
import com.github.xianzhan.jvmjava.java.classfile.cp.MethodDef;
import com.github.xianzhan.jvmjava.java.classfile.cp.StringCp;

/**
 * @author xianzhan
 * @since 2020-06-24
 */
public class JConstantPool {

    public        JClass      clazz;
    private final JConstant[] constArr;

    public JConstantPool(JClass clazz, ConstantPool constantPool) {
        this.clazz = clazz;

        int cpCount = constantPool.infos.length;
        constArr = JConstant.newArray(cpCount);
        for (int i = 0; i < cpCount; i++) {
            var info = constantPool.infos[i];
            if (info instanceof IntegerCp integerCp) {
                constArr[i].val = integerCp.val;
            } else if (info instanceof FloatCp floatCp) {
                constArr[i].val = floatCp.val;
            } else if (info instanceof LongCp longCp) {
                constArr[i].val = longCp.val;
                i++;
            } else if (info instanceof DoubleCp doubleCp) {
                constArr[i].val = doubleCp.val;
                i++;
            } else if (info instanceof StringCp stringCp) {
                constArr[i].val = constantPool.getString(stringCp.stringIndex);
            } else if (info instanceof ClassCp classCp) {
                constArr[i].val = new CpClassRef(this, classCp);
            } else if (info instanceof FieldDef fieldDef) {
                constArr[i].val = new CpFieldRef(this, fieldDef);
            } else if (info instanceof MethodDef methodDef) {
                constArr[i].val = new CpMethodRef(this, methodDef);
            } else if (info instanceof InterfaceMethodDef interfaceMethodDef) {
                constArr[i].val = new CpInterfaceMethodRef(this, interfaceMethodDef);
            }
        }
    }

    public JConstant getConstant(int index) {
        var constant = constArr[index - 1];
        if (constant == null) {
            System.err.println("No constants at index " + index);
        }
        return constant;
    }
}
