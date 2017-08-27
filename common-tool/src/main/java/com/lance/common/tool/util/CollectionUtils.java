package com.lance.common.tool.util;

import java.util.List;
import java.util.ListIterator;

/**
 * @author Lance
 * @since 2017/3/12
 */
public class CollectionUtils {
    private CollectionUtils() {
    }

    /**
     * Merge list by <code>split</code>.
     */
    public static String merge(List<?> list, String split) {
        if (null == list || list.isEmpty()) return "";

        StringBuilder builder = new StringBuilder();
        ListIterator<?> iter = list.listIterator();
        while (iter.hasNext()) {
            builder.append(iter.next());
            if (iter.hasNext()) {
                builder.append(split);
            }
        }

        return builder.toString();
    }
}
