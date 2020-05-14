package com.github.xianzhan.jvmjava.java.util;

import java.util.List;

/**
 * String tools
 *
 * @author xianzhan
 * @since 2020-04-07
 */
public class StringUtils {

    public static boolean contains(String str, String sub) {
        if (isEmpty(str) || isEmpty(sub)) {
            return false;
        }
        return str.contains(sub);
    }

    public static boolean endsWith(String str, String suffix) {
        if (isEmpty(str) || isEmpty(suffix)) {
            return false;
        }
        return str.endsWith(suffix);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static int length(String str) {
        return str == null ? 0 : str.length();
    }

    public static String replace(String str, char oldChar, char newChar) {
        if (str == null) {
            return null;
        }
        return str.replace(oldChar, newChar);
    }

    public static String[] split(String str, String delimiter) {
        if (isEmpty(str) || delimiter == null) {
            return ArrayUtils.EMPTY_STRING;
        }

        List<String> ret = CollectionUtils.newArrayList();
        int beginIndex = 0;
        int endIndex;
        while ((endIndex = str.indexOf(delimiter, beginIndex)) != -1) {
            String substring = str.substring(beginIndex, endIndex);
            ret.add(substring);
            beginIndex = endIndex + 1;
        }
        int length = str.length();
        if (length != beginIndex + 1) {
            ret.add(str.substring(beginIndex, length));
        }
        return ret.toArray(ArrayUtils.EMPTY_STRING);
    }

    public static boolean startsWith(String str, String prefix) {
        if (isEmpty(str) || isEmpty(prefix)) {
            return false;
        }
        return str.startsWith(prefix, 0);
    }
}
