package com.lance.common.tool.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author Lance
 * @since 2022/3/23
 */
public interface CallableFuture<V> extends Callable<V>, Future<V> {
}
