package com.lance.common.tool.concurrent;

import java.util.Objects;

/**
 * 可捕获异常的任务
 *
 * @author Lance
 * @since 2022/3/23
 */
public class CatchableTask implements Runnable {

    private final Runnable delegate;

    private final ErrorHandler errorHandler;

    public CatchableTask(Runnable delegate, ErrorHandler errorHandler) {
        Objects.requireNonNull(delegate, "delegate cannot be null");
        Objects.requireNonNull(errorHandler, "errorHandler cannot be null");
        this.delegate = delegate;
        this.errorHandler = errorHandler;
    }

    @Override
    public void run() {
        try {
            delegate.run();
        } catch (Exception e) {
            errorHandler.handleError(e);
        }
    }
}
