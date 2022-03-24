package com.lance.common.tool.concurrent;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;
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
    public ScheduledFuture<?> scheduleCron(Runnable command, String cron) {
        if (command == null || cron == null) {
            throw new NullPointerException();
        }

        Trigger trigger = new CronTrigger(cron);
        return scheduleTrigger(command, trigger);
    }

    @Override
    public ScheduledFuture<?> scheduleTrigger(Runnable command, Trigger trigger) {
        if (command == null || trigger == null) {
            throw new NullPointerException();
        }

        TriggerTask triggerTask = new TriggerTask(this, command, trigger);
        triggerTask.schedule();
        return triggerTask;
    }

    @Override
    public ScheduledFuture<?> scheduleAtDate(Runnable command, Date date) {
        if (command == null || date == null) {
            throw new NullPointerException();
        }

        return scheduleAtTime(command, date.getTime());
    }

    @Override
    public ScheduledFuture<?> scheduleAtTime(Runnable command, long time) {
        if (command == null) {
            throw new NullPointerException();
        }

        long delay = time - System.currentTimeMillis();
        return schedule(command, delay, TimeUnit.MILLISECONDS);
    }
}
