package com.lance.common.tool;

import java.util.function.Consumer;

/**
 * @author Lance
 */
public class ObjectUtils {

    public static <T> void ifPresent(T obj, Consumer<? super T> consumer) {
        if (obj != null) {
            consumer.accept(obj);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T instance(Class<?> clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T instanceThrows(Class<?> clazz) throws Exception {
        return (T) clazz.newInstance();
    }
}
