package com.github.xianzhan.jvmjava.java.runtime.heap;

import com.github.xianzhan.jvmjava.java.classfile.AccessFlags;
import com.github.xianzhan.jvmjava.java.classfile.ClassFile;
import com.github.xianzhan.jvmjava.java.classloader.ClassLoader;
import com.github.xianzhan.jvmjava.java.runtime.Slot;
import com.github.xianzhan.jvmjava.java.util.Symbol;

import java.util.Objects;

/**
 * 放进方法区的类
 *
 * @author xianzhan
 * @see java.lang.Class
 * @since 2020-06-19
 */
public class JClass {

    public final int           accessFlags;
    public final String        name;
    public final String        superClassName;
    public final String[]      interfaceNames;
    public final JConstantPool constantPool;
    public final JField[]      fields;
    public final JMethod[]     methods;
    public       ClassLoader   loader;
    public       JClass        superClass;
    public       JClass[]      interfaces;
    public       int           instanceSlotCount;
    public       int           staticSlotCount;
    public       Slot[]        staticVars;

    public JClass(ClassFile cf) {
        this.accessFlags = cf.accessFlags;
        this.name = cf.classname();
        this.superClassName = cf.superClassname();
        this.interfaceNames = cf.interfaceNames();
        this.constantPool = new JConstantPool(this, cf.cpInfo);
        this.fields = JField.newFields(this, cf.fields.fields);
        this.methods = JMethod.newMethods(this, cf.methods.methods);
    }

    public boolean isPublic() {
        return (accessFlags & AccessFlags.ACC_PUBLIC) != 0;
    }

    public boolean isFinal() {
        return (accessFlags & AccessFlags.ACC_FINAL) != 0;
    }

    public boolean isSuper() {
        return (accessFlags & AccessFlags.ACC_SUPER) != 0;
    }

    public boolean isInterface() {
        return (accessFlags & AccessFlags.ACC_INTERFACE) != 0;
    }

    public boolean isAbstract() {
        return (accessFlags & AccessFlags.ACC_ABSTRACT) != 0;
    }

    public boolean isSynthetic() {
        return (accessFlags & AccessFlags.ACC_SYNTHETIC) != 0;
    }

    public boolean isAnnotation() {
        return (accessFlags & AccessFlags.ACC_ANNOTATION) != 0;
    }

    public boolean isEnum() {
        return (accessFlags & AccessFlags.ACC_ENUM) != 0;
    }

    public boolean isAccessibleTo(JClass other) {
        return isPublic() || getPackageName().equals(other.getPackageName());
    }

    public boolean isSubClassOf(JClass other) {
        var superClass = this.superClass;
        while (superClass != null) {
            if (Objects.equals(superClass, other)) {
                return true;
            }
            superClass = superClass.superClass;
        }
        return false;
    }

    public boolean isImplements(JClass iFace) {
        var superClass = this;
        while (superClass != null) {
            for (var i : superClass.interfaces) {
                if (i == iFace || i.isSubInterfaceOf(iFace)) {
                    return true;
                }
            }
            superClass = superClass.superClass;
        }

        return false;
    }

    public boolean isSubInterfaceOf(JClass iFace) {
        for (var superInterface : interfaces) {
            if (superInterface == iFace || superInterface.isSubInterfaceOf(iFace)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAssignableFrom(JClass other) {
        if (this == other) {
            return true;
        }

        return isInterface() ?
                other.isSubClassOf(this) :
                other.isImplements(this);
    }

    public String getPackageName() {
        var i = name.lastIndexOf('/');
        if (i >= 0) {
            return name.substring(0, i);
        }
        return "";
    }

    public JMethod getMainMethod() {
        return getStaticMethod(Symbol.METHOD_MAIN, Symbol.DESCRIPTOR_STR_ARR_V);
    }

    private JMethod getStaticMethod(String name, String descriptor) {
        for (var method : methods) {
            if (method.isStatic() &&
                Objects.equals(name, method.name) &&
                Objects.equals(descriptor, method.descriptor)) {

                return method;
            }
        }

        return null;
    }

    public JConstantPool constantPool() {
        return constantPool;
    }

    public Slot[] staticVars() {
        return staticVars;
    }

    public JObject newObject() {
        return new JObject(this);
    }
}
