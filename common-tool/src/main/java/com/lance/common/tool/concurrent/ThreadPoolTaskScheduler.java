package com.lance.common.tool.concurrent;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 线程池任务调度器
 *
 * @author Lance
 * @since 2022/3/24
 */
public class ThreadPoolTaskScheduler extends ScheduledThreadPoolExecutor implements TaskScheduler {

    private ErrorHandler errorHandler;

    public ThreadPoolTaskScheduler(int corePoolSize) {
        super(corePoolSize);
    }

    public ThreadPoolTaskScheduler(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

    public ThreadPoolTaskScheduler(int corePoolSize, RejectedExecutionHandler handler) {
        super(corePoolSize, handler);
    }

    public ThreadPoolTaskScheduler(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, threadFactory, handler);
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return super.schedule(decorate(command), delay, unit);
    }

    @Override
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        return super.schedule(decorate(callable), delay, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        return super.scheduleAtFixedRate(decorate(command), initialDelay, period, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        return super.scheduleWithFixedDelay(decorate(command), initialDelay, delay, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleCron(Runnable command, String cron) {
        if (command == null || cron == null) {
            throw new NullPointerException();
        }

        Trigger trigger = new CronTrigger(cron);
        return scheduleTrigger(decorate(command), trigger);
    }

    @Override
    public ScheduledFuture<?> scheduleTrigger(Runnable command, Trigger trigger) {
        if (command == null || trigger == null) {
            throw new NullPointerException();
        }

        TriggerTask triggerTask = new TriggerTask(this, decorate(command), trigger);
        triggerTask.schedule();
        return triggerTask;
    }

    @Override
    public ScheduledFuture<?> scheduleAtDate(Runnable command, Date date) {
        if (command == null || date == null) {
            throw new NullPointerException();
        }

        return scheduleAtTime(decorate(command), date.getTime());
    }

    @Override
    public ScheduledFuture<?> scheduleAtTime(Runnable command, long time) {
        if (command == null) {
            throw new NullPointerException();
        }

        long delay = time - System.currentTimeMillis();
        return schedule(decorate(command), delay, TimeUnit.MILLISECONDS);
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    private Runnable decorate(Runnable runnable) {
        if (errorHandler != null) {
            runnable = new CatchableRunner(runnable, errorHandler);
        }
        return runnable;
    }

    private <V> Callable<V> decorate(Callable<V> callable) {
        if (errorHandler != null) {
            callable = new CatchableCaller<>(callable, errorHandler);
        }
        return callable;
    }
}
