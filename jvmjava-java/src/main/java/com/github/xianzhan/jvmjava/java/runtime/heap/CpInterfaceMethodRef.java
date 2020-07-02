package com.github.xianzhan.jvmjava.java.runtime.heap;

import com.github.xianzhan.jvmjava.java.classfile.cp.InterfaceMethodDef;

import static com.github.xianzhan.jvmjava.java.runtime.heap.CpMethodRef.lookupMethodInInterfaces;

/**
 * @author xianzhan
 * @since 2020-06-24
 */
public class CpInterfaceMethodRef extends CpMemberRef {

    private JMethod method;

    public CpInterfaceMethodRef(JConstantPool cp, InterfaceMethodDef refInfo) {
        this.cp = cp;
        copyMemberRefInfo(
                refInfo.className(),
                refInfo.name(),
                refInfo.descriptor()
        );
    }

    public JMethod resolvedInterfaceMethod() {
        if (method == null) {
            resolveInterfaceMethodRef();
        }
        return method;
    }

    private void resolveInterfaceMethodRef() {
        var d = cp.clazz;
        var c = resolvedClass();
        if (!c.isInterface()) {
            throw new IncompatibleClassChangeError(c.name);
        }

        var method = lookupInterfaceMethod(c, name(), descriptor());
        if (method == null) {
            throw new NoSuchMethodError(name());
        }
        if (!method.isAccessibleTo(d)) {
            throw new IllegalAccessError(name());
        }
        this.method = method;
    }

    private JMethod lookupInterfaceMethod(JClass iFace, String name, String descriptor) {
        for (var method : iFace.methods) {
            if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                return method;
            }
        }

        return lookupMethodInInterfaces(iFace.interfaces, name, descriptor);
    }
}
