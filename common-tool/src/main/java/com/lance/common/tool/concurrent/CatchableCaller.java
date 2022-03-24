package com.lance.common.tool.concurrent;

import java.util.concurrent.Callable;

/**
 * 可捕获异常的任务
 *
 * @author Lance
 * @since 2022/3/24
 */
public class CatchableCaller<V> implements Callable<V> {

    private final Callable<V> delegate;

    private final ErrorHandler errorHandler;

    public CatchableCaller(Callable<V> delegate, ErrorHandler errorHandler) {
        this.delegate = delegate;
        this.errorHandler = errorHandler;
    }

    @Override
    public V call() throws Exception {
        try {
            return delegate.call();
        } catch (Throwable e) {
            errorHandler.handleError(e);
        }
        return null;
    }
}
