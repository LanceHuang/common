package com.lance.common.tool.concurrent;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * cron调度器，在ScheduledThreadPoolExecutor的基础上添加了cron
 *
 * @author Lance
 * @since 2022/3/23
 */
public class ScheduledCronExecutor extends ScheduledThreadPoolExecutor implements CronExecutorService {

    public ScheduledCronExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    public ScheduledCronExecutor(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

    public ScheduledCronExecutor(int corePoolSize, RejectedExecutionHandler handler) {
        super(corePoolSize, handler);
    }

    public ScheduledCronExecutor(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, threadFactory, handler);
    }

    @Override
    public ScheduledFuture<?> scheduleCron(Runnable command, String cron) {
        if (command == null || cron == null) {
            throw new NullPointerException();
        }

        CronTask cronTask = new CronTask(this, command, cron);
        cronTask.schedule();
        return cronTask;
    }
}
