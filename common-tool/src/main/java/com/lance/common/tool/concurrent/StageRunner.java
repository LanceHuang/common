package com.lance.common.tool.concurrent;

/**
 * 可分阶段的runner
 *
 * @author Lance
 * @since 2022/3/24
 */
public class StageRunner implements Runnable {

    private final Runnable delegate;

    private final TaskStage taskStage;

    public StageRunner(Runnable delegate, TaskStage taskStage) {
        this.delegate = delegate;
        this.taskStage = taskStage;
    }

    @Override
    public void run() {
        try {
            taskStage.handleBefore(delegate);
            delegate.run();
            taskStage.handleAfter(delegate);
        } catch (Throwable e) {
            taskStage.handleError(delegate, e);
        } finally {
            taskStage.handleFinal(delegate);
        }
    }
}
