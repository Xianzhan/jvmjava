package com.github.xianzhan.jvmjava.java.runtime.heap;

/**
 * 字段符号引用
 * <p>
 * 存放字段和方法
 * 符号引用共有的信息
 *
 * @author xianzhan
 * @since 2020-06-24
 */
public class CpMemberRef extends CpSymRef {

    private String name;
    private String descriptor;

    protected void copyMemberRefInfo(String className, String name, String descriptor) {
        this.className = className;
        this.name = name;
        this.descriptor = descriptor;
    }

    public String name() {
        return name;
    }

    public String descriptor() {
        return descriptor;
    }

    @Override
    public String toString() {
        return "CpMemberRef#" + name + descriptor;
    }
}
