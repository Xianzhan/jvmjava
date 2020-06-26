package com.github.xianzhan.jvmjava.java.runtime.heap;

import com.github.xianzhan.jvmjava.java.classfile.AccessFlags;
import com.github.xianzhan.jvmjava.java.classfile.Member;

import java.util.Objects;

/**
 * @author xianzhan
 * @since 2020-06-21
 */
public class JClassMember {

    protected int    accessFlags;
    protected String name;
    protected String descriptor;
    protected JClass clazz;

    public void copyMemberInfo(Member member) {
        accessFlags = member.accessFlags();
        name = member.name();
        descriptor = member.descriptor();
    }

    public boolean isPublic() {
        return (accessFlags & AccessFlags.ACC_PUBLIC) != 0;
    }

    public boolean isPrivate() {
        return (accessFlags & AccessFlags.ACC_PRIVATE) != 0;
    }

    public boolean isProtected() {
        return (accessFlags & AccessFlags.ACC_PROTECTED) != 0;
    }

    public boolean isStatic() {
        return (accessFlags & AccessFlags.ACC_STATIC) != 0;
    }

    public boolean isFinal() {
        return (accessFlags & AccessFlags.ACC_FINAL) != 0;
    }

    public boolean isSynthetic() {
        return (accessFlags & AccessFlags.ACC_SYNTHETIC) != 0;
    }

    public boolean isAccessibleTo(JClass d) {
        if (isPublic()) {
            return true;
        }

        var c = clazz;
        if (isProtected()) {
            return d == c ||
                   d.isSubClassOf(c) ||
                   Objects.equals(c.getPackageName(), d.getPackageName());
        }

        if (!isPrivate()) {
            return Objects.equals(c.getPackageName(), d.getPackageName());
        }

        return d == c;
    }

    public String name() {
        return name;
    }

    public String descriptor() {
        return descriptor;
    }

    public JClass clazz() {
        return clazz;
    }
}
