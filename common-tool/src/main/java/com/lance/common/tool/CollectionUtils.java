package com.lance.common.tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Lance
 * @since 2022/1/20
 */
public class CollectionUtils {

    public static <T> ArrayList<T> arrayList() {
        return new ArrayList<>();
    }

    public static <T> ArrayList<T> arrayList(int initialCapacity) {
        return new ArrayList<>(initialCapacity);
    }

    public static <T> ArrayList<T> arrayList(Collection<T> c) {
        return new ArrayList<>(c);
    }

    public static <T> ArrayList<T> arrayList(T... items) {
        ArrayList<T> list = arrayList(items.length);
        list.addAll(Arrays.asList(items));
        return list;
    }

    /**
     * 类似于Collections.singletonList
     *
     * @param item 项
     * @param <T>  类型
     * @return 单项列表
     */
    public static <T> ArrayList<T> singletonList(T item) {
        ArrayList<T> list = arrayList();
        list.add(item);
        return list;
    }

    public static <K, V> HashMap<K, V> hashMap() {
        return new HashMap<>();
    }

    public static <K, V> HashMap<K, V> hashMap(int initialCapacity) {
        return new HashMap<>(initialCapacity);
    }

    public static <K, V> HashMap<K, V> hashMap(Map<K, V> map) {
        return new HashMap<>(map);
    }

    public static <K, V> HashMap<K, V> singletonMap(K k, V v) {
        HashMap<K, V> hashMap = hashMap();
        hashMap.put(k, v);
        return hashMap;
    }

    public static <T> HashSet<T> hashSet() {
        return new HashSet<>();
    }

    public static <T> HashSet<T> hashSet(int initialCapacity) {
        return new HashSet<>(initialCapacity);
    }

    public static <T> HashSet<T> hashSet(Collection<T> c) {
        return new HashSet<>(c);
    }

    public static <T> HashSet<T> singletonSet(T item) {
        HashSet<T> hashSet = hashSet();
        hashSet.add(item);
        return hashSet;
    }

}
