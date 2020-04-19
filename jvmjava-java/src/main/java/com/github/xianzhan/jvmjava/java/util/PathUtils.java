package com.github.xianzhan.jvmjava.java.util;

import java.nio.file.Path;

/**
 * @author xianzhan
 * @since 2020-04-08
 */
public class PathUtils {

    private static final int    COMPRESS_LENGTH_LIMIT = 4;
    private static final String DOT_JAR               = ".jar";
    private static final String DOT_ZIP               = ".zip";

    public static final String DOT_CLASS = ".class";

    /**
     * Match all paths
     */
    public static String ALL = "*";

    /**
     * Path separator (":" on Unix, ";" on Windows)
     */
    public static String PATH_SEPARATOR = System.getProperty("path.separator");

    /**
     * Return absolute path
     *
     * @param path relative path
     * @return absolute path
     */
    public static String absolute(String path) {
        return Path.of(path).toAbsolutePath().toString();
    }

    /**
     * Is a compressed file
     *
     * @param path path
     * @return true is compressed file
     */
    public static boolean isCompressedName(String path) {
        if (StringUtils.length(path) <= COMPRESS_LENGTH_LIMIT) {
            return false;
        }

        String lowerExtensionName = path.substring(path.length() - COMPRESS_LENGTH_LIMIT).toLowerCase();
        return DOT_JAR.equals(lowerExtensionName) || DOT_ZIP.equals(lowerExtensionName);
    }

    /**
     * Dot to slash
     *
     * @param classname classname
     * @return path
     */
    public static String toClassfilePath(String classname) {
        return StringUtils.replace(classname, '.', '/');
    }
}
