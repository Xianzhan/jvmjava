package com.github.xianzhan.jvmjava.java.classpath;

import com.github.xianzhan.jvmjava.java.util.Strings;

import java.io.File;

/**
 * classpath 入口
 *
 * @author xianzhan
 * @since 2020-02-27
 */
public abstract class Entry {

    public static final String PATH_LIST_SEPARATOR = File.pathSeparator;

    private String    path;
    private byte[]    bytes;
    private Exception error;

    public Entry(String path) {
        this.path = path;
    }

    /**
     * 负责寻找和加载 class 文件
     *
     * @param className 文件路径
     * @return 实例
     */
    public abstract Entry readClass(String className);

    public static Entry newEntry(String path) {
        if (path.contains(PATH_LIST_SEPARATOR)) {
            return new CompositeEntry(path);
        } else if (Strings.hasSuffix(path, "*")) {
            return new WildcardEntry(path);
        } else if (isCompressed(path)) {
            return new ZipEntry(path);
        }
        return new DirEntry(path);
    }

    protected void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    protected void setError(Exception error) {
        this.error = error;
    }

    public Exception getError() {
        return error;
    }

    private static boolean isCompressed(String path) {
        return Strings.hasSuffix(path, ".jar") || Strings.hasSuffix(path, ".JAR")
                || Strings.hasSuffix(path, ".zip") || Strings.hasSuffix(path, ".ZIP");
    }
}
