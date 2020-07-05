package com.github.xianzhan.jvmjava.java.runtime.heap;

import com.github.xianzhan.jvmjava.java.util.AccessFlags;
import com.github.xianzhan.jvmjava.java.classfile.Member;
import com.github.xianzhan.jvmjava.java.util.Symbol;

/**
 * @author xianzhan
 * @since 2020-06-23
 */
public class JField extends JClassMember {

    private int constValueIndex;
    public  int slotIdx;

    private JField() {
    }

    public static JField[] newFields(JClass clazz, Member[] cfFields) {
        final int len = cfFields.length;
        var fields = new JField[len];
        for (int i = 0; i < len; i++) {
            var field = cfFields[i];
            var jField = new JField();
            jField.clazz = clazz;
            jField.copyMemberInfo(field);
            jField.copyAttributes(field);

            fields[i] = jField;
        }
        return fields;
    }

    private void copyAttributes(Member field) {
        var constantValue = field.constantValueAttribute();
        if (constantValue != null) {
            constValueIndex = constantValue.constantValueIndex();
        }
    }

    public boolean isVolatile() {
        return (accessFlags & AccessFlags.ACC_VOLATILE) != 0;
    }

    public boolean isTransient() {
        return (accessFlags & AccessFlags.ACC_TRANSIENT) != 0;
    }

    public boolean isEnum() {
        return (accessFlags & AccessFlags.ACC_ENUM) != 0;
    }

    public boolean isLongOrDouble() {
        return Symbol.DESCRIPTOR_LONG.equals(descriptor) ||
               Symbol.DESCRIPTOR_DOUBLE.equals(descriptor);
    }

    public int constValueIndex() {
        return constValueIndex;
    }

    @Override
    public String toString() {
        return "JField#" + clazz.name + "." + name + ":" + descriptor;
    }
}
