package com.lance.common.tool.concurrent;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * cron任务。这里没必要实现ScheduledFuture，ScheduledThreadPoolExecutor中会直接将Future添加到队列中，所以需要用到Delayed
 *
 * @author Lance
 * @since 2022/3/23
 */
public class CronTask extends FutureTask<Object> {

    /** 执行器 */
    private final ScheduledExecutorService scheduledExecutorService;

    /** cron时间计算 */
    private final SimpleTriggerContext triggerContext = new SimpleTriggerContext();
    private final Trigger trigger;
    private Date scheduledExecutionTime;

    public CronTask(ScheduledExecutorService scheduledExecutorService, Runnable delegate, String cron) {
        super(delegate, null);
        this.scheduledExecutorService = scheduledExecutorService;
        this.trigger = new CronTrigger(cron);
    }

    @Override
    public void run() {
        if (!isCancelled()) {
            // 执行
            Date actualExecutionTime = new Date();
            runAndReset();
            Date completionTime = new Date();
            triggerContext.update(scheduledExecutionTime, actualExecutionTime, completionTime);

            // 计算下一轮
            schedule();
        }
    }

    /**
     * 调度下一个任务
     *
     * @return 任务future
     */
    public Future<?> schedule() {
        this.scheduledExecutionTime = trigger.nextExecutionTime(triggerContext);
        if (this.scheduledExecutionTime == null) {
            return null;
        }

        // 可直接操作cancel，不需要ScheduledExecutorService.schedule的返回值
        long delay = this.scheduledExecutionTime.getTime() - System.currentTimeMillis();
        this.scheduledExecutorService.schedule(this, delay, TimeUnit.MILLISECONDS);
        return this;
    }
}