package com.lance.common.tool.concurrent;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * cron任务。这里不实现Runnable接口，而是实现Callable接口，在ScheduledThreadPoolExecutor中可以减少一个对象的创建。
 * 参考org.springframework.scheduling.concurrent.ReschedulingRunnable
 *
 * @author Lance
 * @since 2022/3/23
 */
public class CronTask implements Callable<Object>, ScheduledFuture<Object> {

    /** 实际命令 */
    private final ScheduledExecutorService scheduledExecutorService;
    private final Runnable delegate;

    /** cron时间计算 */
    private final SimpleTriggerContext triggerContext = new SimpleTriggerContext();
    private final Trigger trigger;
    private Date scheduledExecutionTime;

    /** future处理：多个任务会产生多个ScheduledFuture，需要CronFuture统一管理 */
    private ScheduledFuture<?> currentFuture;

    /** 锁：防止并发访问currentFuture */
    private final Object mutex = new Object();

    public CronTask(ScheduledExecutorService scheduledExecutorService, Runnable delegate, String cron) {
        Objects.requireNonNull(scheduledExecutorService, "scheduledExecutorService cannot be null");
        Objects.requireNonNull(delegate, "delegate cannot be null");
        Objects.requireNonNull(cron, "cron cannot be null");
        this.scheduledExecutorService = scheduledExecutorService;
        this.delegate = delegate;
        this.trigger = new CronTrigger(cron);
    }

    @Override
    public Object call() {
        synchronized (mutex) {
            if (!currentFuture.isCancelled()) {
                // 执行
                Date actualExecutionTime = new Date();
                delegate.run();
                Date completionTime = new Date();
                triggerContext.update(scheduledExecutionTime, actualExecutionTime, completionTime);

                // 计算下一轮
                schedule();
            }
        }
        return null;
    }

    /**
     * 调度下一个任务
     *
     * @return 任务future
     */
    public ScheduledFuture<?> schedule() {
        this.scheduledExecutionTime = trigger.nextExecutionTime(triggerContext);
        if (this.scheduledExecutionTime == null) {
            return null;
        }

        long delay = this.scheduledExecutionTime.getTime() - System.currentTimeMillis();
        this.currentFuture = scheduledExecutorService.schedule(this, delay, TimeUnit.MILLISECONDS);
        return this;
    }

    public ScheduledFuture<?> getCurrentFuture() {
        return currentFuture;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // 不需要实现，只有ScheduledThreadPoolExecutor内部会将任务直接添加到队列
        synchronized (mutex) {
            return getCurrentFuture().getDelay(unit);
        }
    }

    @Override
    public int compareTo(Delayed o) {
        // 不需要实现，只有ScheduledThreadPoolExecutor内部会将任务直接添加到队列
        synchronized (mutex) {
            return getCurrentFuture().compareTo(o);
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        synchronized (mutex) {
            return getCurrentFuture().cancel(mayInterruptIfRunning);
        }
    }

    @Override
    public boolean isCancelled() {
        synchronized (mutex) {
            return getCurrentFuture().isCancelled();
        }
    }

    @Override
    public boolean isDone() {
        synchronized (mutex) {
            return getCurrentFuture().isDone();
        }
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        synchronized (mutex) {
            return getCurrentFuture().get();
        }
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        synchronized (mutex) {
            return getCurrentFuture().get(timeout, unit);
        }
    }

}