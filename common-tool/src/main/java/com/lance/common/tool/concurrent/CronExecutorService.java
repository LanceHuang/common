package com.lance.common.tool.concurrent;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

/**
 * cron执行器
 *
 * @author Lance
 * @since 2022/3/23
 */
public interface CronExecutorService extends ScheduledExecutorService {

    /**
     * 创建并执行cron任务
     *
     * @param command 执行指令
     * @param cron    表达式
     * @return future
     */
    Future<?> scheduleCron(Runnable command, String cron);
}
