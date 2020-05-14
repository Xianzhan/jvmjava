package com.github.xianzhan.jvmjava.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author xianzhan
 * @since 2020-04-11
 */
public class CollectionUtils {

    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @param <T>        元素类型
     * @return true 集合为 null 或者 empty
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> List<T> newArrayList(int initialCapacity) {
        return new ArrayList<>(initialCapacity);
    }

    public static <T> List<T> newArrayList(T[] arr) {
        return new ArrayList<T>(Arrays.asList(arr));
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>(16);
    }
}
