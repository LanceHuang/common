package com.lance.common.tool.concurrent;

/**
 * 异常处理
 *
 * @author Lance
 * @since 2022/3/23
 */
public interface ErrorHandler {

    /**
     * 处理异常
     *
     * @param t 异常
     */
    void handleError(Throwable t);
}
