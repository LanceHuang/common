package com.lance.common.tool.concurrent;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * cron调度器。JDK自带的ScheduledThreadPoolExecutor不支持Cron，Spring schedule不支持热更
 *
 * @author Lance
 * @since 2022/3/23
 */
public class CronScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor implements CronScheduledExecutorService {

    public CronScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    public CronScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

    public CronScheduledThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler handler) {
        super(corePoolSize, handler);
    }

    public CronScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
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
