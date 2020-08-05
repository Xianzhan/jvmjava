package xianzhan.jvmjava.java.util;

import java.util.Objects;

/**
 * Array tools
 *
 * @author xianzhan
 * @since 2020-04-06
 */
public class ArrayUtils {

    public static String[] EMPTY_STRING = {};

    @SafeVarargs
    public static <T> boolean anyContains(T[] arr, T... ele) {
        for (T t : ele) {
            if (indexOf(arr, t) > -1) {
                return true;
            }
        }
        return false;
    }

    public static <T> int indexOf(T[] arr, T ele) {
        if (isEmpty(arr)) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (Objects.equals(ele, arr[i])) {
                return i;
            }
        }
        return -1;
    }

    public static <T> boolean isEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }
}
