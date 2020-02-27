package com.github.xianzhan.jvmjava.java.util;

/**
 * 字符串工具类
 *
 * @author xianzhan
 * @since 2020-02-27
 */
public final class Strings {

    public static final String[] EMPTY_ARRAY = new String[0];

    public static boolean hasSuffix(String s, String suffix) {
        return len(s) >= len(suffix) && s.substring(len(s) - len(suffix)).equals(suffix);
    }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static int len(String s) {
        return s == null ? 0 : s.length();
    }

    public static String[] split(String s, String regex) {
        return s == null ? EMPTY_ARRAY : s.split(regex);
    }
}
