package com.lance.common.tool.concurrent;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 触发型任务
 *
 * @author Lance
 * @since 2022/3/23
 */
public class TriggerTask extends FutureCaller<Object> implements ScheduledFuture<Object> {

    /** 执行器 */
    private final ScheduledExecutorService scheduledExecutorService;

    /** trigger */
    private final SimpleTriggerContext triggerContext = new SimpleTriggerContext();
    private final Trigger trigger;
    private Date scheduledExecutionTime;

    public TriggerTask(ScheduledExecutorService scheduledExecutorService, Runnable delegate, Trigger trigger) {
        super(delegate, null);
        this.scheduledExecutorService = scheduledExecutorService;
        this.trigger = trigger;
    }

    @Override
    public Object call() {
        if (!isCancelled()) {
            // 执行
            Date actualExecutionTime = new Date();
            runAndReset();
            Date completionTime = new Date();
            triggerContext.update(scheduledExecutionTime, actualExecutionTime, completionTime);

            // 计算下一轮：这里再做一次判断，上面的runAndReset可能会比较耗时，到这的时候如果被cancel，没必要调用schedule
            if (!isCancelled()) {
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

        // 可直接操作cancel，不需要ScheduledExecutorService.schedule的返回值
        // 如果想cancel掉该任务，只需要调用TriggerTask.cancel，调用TriggerTask.run时会做判断
        long delay = this.scheduledExecutionTime.getTime() - System.currentTimeMillis();
        this.scheduledExecutorService.schedule(this, delay, TimeUnit.MILLISECONDS);
        return this;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.scheduledExecutionTime.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        // 不需要实现，ScheduledThreadPoolExecutor会直接将ScheduledFuture添加到队列中，所以需要用到Delayed
        // 这里实现ScheduledFuture，只是为了让调用者可以调用getDelay，获取到下个任务执行的时间
        return 0;
    }
}