package xianzhan.jvmjava.java.runtime.heap;

import xianzhan.jvmjava.java.classfile.cp.MethodDef;

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

    private void resolveMethodRef() {
        var d = cp.clazz;
        var c = resolvedClass();
        if (c.isInterface()) {
            throw new IncompatibleClassChangeError(c.name);
        }

        var method = lookupMethod(c, name(), descriptor());
        if (method == null) {
            throw new NoSuchMethodError(name());
        }
        if (!method.isAccessibleTo(d)) {
            throw new IllegalAccessError(name());
        }
        this.method = method;
    }

    // 查找方法

    private JMethod lookupMethod(JClass c, String name, String descriptor) {
        var method = lookupMethodInClass(c, name, descriptor);
        if (method == null) {
            method = lookupMethodInInterfaces(c.interfaces, name, descriptor);
        }
        return method;
    }

    public static JMethod lookupMethodInClass(JClass clazz, String name, String descriptor) {
        for (var c = clazz; c != null; c = c.superClass) {
            var methods = c.methods;
            if (methods == null) {
                continue;
            }

            for (var method : methods) {
                if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }

    public static JMethod lookupMethodInInterfaces(JClass[] iFaces, String name, String descriptor) {
        for (var iFace : iFaces) {
            for (var method : iFace.methods) {
                if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                    return method;
                }
            }

            var method = lookupMethodInInterfaces(iFace.interfaces, name, descriptor);
            if (method != null) {
                return method;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "CpMethodRef#" + className + "." + name() + descriptor();
    }
}
