package com.lance.common.tool.util;

import java.util.function.Consumer;

/**
 * @author Lance
 */
public class ObjectUtils {

    public static <T> void ifPresent(T obj, Consumer<? super T> consumer) {
        if (obj != null)
            consumer.accept(obj);
    }

}
