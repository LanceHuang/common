package com.lance.common.tool.concurrent;

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
