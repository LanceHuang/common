package com.lance.common.tool.concurrent;

/**
 * 任务阶段处理。业务开发中，经常用到
 * <pre>
 * long t1 = System.currentTimeMills();
 * try {
 *     delegate.run();
 * } catch (Exception e) {
 *     logger.error("exception occurs", e);
 * } final {
 *     long t2 = System.currentTimeMills();
 *     long useTime = t2 - t1;
 *     statManager.stat(taskName, useTime);
 * }
 * </pre>
 *
 * @author Lance
 * @since 2022/3/24
 */
public interface TaskStage {

    /**
     * 前处理
     *
     * @param runnable 任务
     */
    void handleBefore(Runnable runnable);

    /**
     * 后处理
     *
     * @param runnable 任务
     */
    void handleAfter(Runnable runnable);

    /**
     * 异常处理
     *
     * @param runnable 任务
     * @param t        异常
     */
    void handleError(Runnable runnable, Throwable t);

    /**
     * final处理
     *
     * @param runnable 任务
     */
    void handleFinal(Runnable runnable);
}
