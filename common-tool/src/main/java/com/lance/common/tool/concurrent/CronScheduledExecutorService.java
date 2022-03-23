package com.lance.common.tool.concurrent;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 * cron执行器
 *
 * @author Lance
 * @since 2022/3/23
 */
public interface CronScheduledExecutorService extends ScheduledExecutorService {

    /**
     * 创建并执行cron任务
     *
     * @param command 执行指令
     * @param cron    表达式
     * @return future
     */
    ScheduledFuture<?> scheduleCron(Runnable command, String cron);
}
