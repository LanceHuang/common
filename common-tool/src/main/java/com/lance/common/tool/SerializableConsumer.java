package com.lance.common.tool;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * @author Lance
 * @since 2020/12/31
 */
public interface SerializableConsumer<T> extends Consumer<T>, Serializable {
}
