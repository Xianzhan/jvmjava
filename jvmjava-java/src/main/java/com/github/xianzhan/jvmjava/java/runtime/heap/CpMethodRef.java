package com.github.xianzhan.jvmjava.java.runtime.heap;

import com.github.xianzhan.jvmjava.java.classfile.cp.MethodDef;

import java.util.Objects;

/**
 * @author xianzhan
 * @since 2020-06-24
 */
public class CpMethodRef extends CpMemberRef {

    private JMethod method;

    public CpMethodRef(JConstantPool cp, MethodDef refInfo) {
        this.cp = cp;
        copyMemberRefInfo(
                refInfo.className(),
                refInfo.name(),
                refInfo.descriptor()
        );
    }

    public JMethod resolvedMethod() {
        if (method == null) {
            resolveMethodRef();
        }
        return method;
    }

    public void resolveMethodRef() {
        var d = cp.clazz;
        var c = resolvedClass();
        method = lookupMethod(c, name(), descriptor());

        if (method == null) {
            throw new RuntimeException("java.lang.NoSuchMethodError " + name());
        }
        if (!method.isAccessibleTo(d)) {
            throw new RuntimeException("java.lang.IllegalAccessError " + name());
        }
    }

    private JMethod lookupMethod(JClass c, String name, String descriptor) {
        for (var m : c.methods) {
            if (Objects.equals(m.name, name) && Objects.equals(m.descriptor, descriptor)) {
                return m;
            }
        }

        for (var iFace : c.interfaces) {
            var jMethod = lookupMethod(iFace, name, descriptor);
            if (jMethod != null) {
                return jMethod;
            }
        }

        if (c.superClass != null) {
            return lookupMethod(c.superClass, name, descriptor);
        }

        return null;
    }
}
