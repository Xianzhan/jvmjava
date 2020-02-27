package com.github.xianzhan.jvmjava.java.util;

import java.io.File;
import java.nio.file.Path;

/**
 * 文件目录工具类
 *
 * @author xianzhan
 * @since 2020-02-27
 */
public class Filepath {

    public static String abs(String path) {
        return Path.of(path).toAbsolutePath().toString();
    }

    public static String join(String... paths) {
        return String.join(File.separator, paths);
    }
}
