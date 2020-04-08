package com.github.xianzhan.jvmjava.java.util;

/**
 * String tools
 *
 * @author xianzhan
 * @since 2020-04-07
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean startsWith(String str, String prefix) {
        if (isEmpty(str)) {
            return false;
        }
        return str.startsWith(prefix, 0);
    }
}
