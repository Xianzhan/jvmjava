package com.github.xianzhan.jvmjava.java.classfile;

import com.github.xianzhan.jvmjava.java.classfile.cp.ClassCp;
import com.github.xianzhan.jvmjava.java.classfile.cp.NameAndType;
import com.github.xianzhan.jvmjava.java.classfile.cp.Utf8;

/**
 * 常量池
 * 1. 表头给出的常量池大小比实际大 1
 * 假设表头给出的值是 n, 那么常量池的实际大小时 n-1
 * 2. 有效的常量池索引是 1~n-1, 0 是无效索引, 表示不指向任何常量
 * 3. CONSTANT_Long_info和 CONSTANT_Double_info 各占两个位置。
 * 也就是说，如果常量池中存在这两种常量，实际的常量数量比 n–1 还要少，
 * 而且1~n–1的某些数也会变成无效索引。
 *
 * @author xianzhan
 * @since 2020-05-17
 */
public class ConstantPool {

    public final ConstantInfo[] infos;

    public ConstantPool(int size) {
        this.infos = new ConstantInfo[size];
    }

    public String getClassName(int idx) {
        ClassCp classCp = (ClassCp) infos[idx - 1];
        return getString(classCp.nameIndex);
    }

    public String getString(int idx) {
        Utf8 utf8 = (Utf8) infos[idx - 1];
        return utf8.getString();
    }

    /**
     * 返回字段名和字段描述符
     *
     * @param idx nameAndTypeIndex
     * @return String[] {hash, I}
     */
    public String[] getNameAndType(int idx) {
        NameAndType nameAndType = (NameAndType) infos[idx - 1];
        return new String[]{getString(nameAndType.nameIndex), getString(nameAndType.descriptionIndex)};
    }
}
