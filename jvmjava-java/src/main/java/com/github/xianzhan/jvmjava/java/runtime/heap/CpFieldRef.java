package com.github.xianzhan.jvmjava.java.runtime.heap;

import com.github.xianzhan.jvmjava.java.classfile.cp.FieldDef;

import java.util.Objects;

/**
 * @author xianzhan
 * @since 2020-06-24
 */
public class CpFieldRef extends CpMemberRef {

    private JField field;

    public CpFieldRef(JConstantPool cp, FieldDef refInfo) {
        this.cp = cp;
        copyMemberRefInfo(
                refInfo.className(),
                refInfo.name(),
                refInfo.descriptor()
        );
    }

    public JField resoledField() {
        if (field == null) {
            resoleFieldRef();
        }
        return field;
    }

    private void resoleFieldRef() {
        var d = cp.clazz;
        var c = resolvedClass();
        field = lookupField(c, name(), descriptor());

        if (field == null) {
            throw new RuntimeException("java.lang.NoSuchFieldError " + name());
        }
        if (!field.isAccessibleTo(d)) {
            throw new RuntimeException("java.lang.IllegalAccessError " + name());
        }
    }

    private JField lookupField(JClass c, String name, String descriptor) {
        for (var f : c.fields) {
            if (Objects.equals(f.name, name) && Objects.equals(f.descriptor, descriptor)) {
                return f;
            }
        }

        for (var iFace : c.interfaces) {
            var jField = lookupField(iFace, name, descriptor);
            if (jField != null) {
                return jField;
            }
        }

        if (c.superClass != null) {
            return lookupField(c.superClass, name, descriptor);
        }

        return null;
    }
}
