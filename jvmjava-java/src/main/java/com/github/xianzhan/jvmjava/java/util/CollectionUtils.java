package com.github.xianzhan.jvmjava.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xianzhan
 * @since 2020-04-11
 */
public class CollectionUtils {

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> List<T> newArrayList(int initialCapacity) {
        return new ArrayList<>(initialCapacity);
    }

    public static <T> List<T> newArrayList(T[] arr) {
        return new ArrayList<T>(Arrays.asList(arr));
    }
}
