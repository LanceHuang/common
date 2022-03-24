package com.lance.common.tool.concurrent;

import org.springframework.scheduling.Trigger;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 * 任务调度器
 *
 * @author Lance
 * @since 2022/3/24
 */
public interface TaskScheduler extends ScheduledExecutorService {

    /**
     * 创建并执行cron任务
     *
     * @param command 执行指令
     * @param cron    表达式
     * @return future
     */
    ScheduledFuture<?> scheduleCron(Runnable command, String cron);

    /**
     * 创建并执行Trigger任务
     *
     * @param command 执行指令
     * @param trigger 触发器
     * @return future
     */
    ScheduledFuture<?> scheduleTrigger(Runnable command, Trigger trigger);

    /**
     * 创建并执行Date任务
     *
     * @param command 执行指令
     * @param date    执行任务时间点
     * @return future
     */
    ScheduledFuture<?> scheduleAtDate(Runnable command, Date date);

    /**
     * 创建并执行time任务
     *
     * @param command 执行指令
     * @param time    执行任务时间点
     * @return future
     */
    ScheduledFuture<?> scheduleAtTime(Runnable command, long time);
}
