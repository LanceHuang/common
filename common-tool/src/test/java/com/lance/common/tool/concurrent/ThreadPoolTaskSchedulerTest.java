package com.lance.common.tool.concurrent;

import com.lance.common.tool.ThreadUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lance
 * @since 2022/3/24
 */
class ThreadPoolTaskSchedulerTest {

    @Test
    void testScheduleCron() throws InterruptedException {
        TaskScheduler taskScheduler = new ThreadPoolTaskScheduler(1);
        ScheduledFuture<?> helloFuture = taskScheduler.scheduleCron(() -> System.out.println("Hello world"), "*/1 * * * * *");
        ThreadUtils.sleep(5000L);
        System.out.println("Hello delay: " + helloFuture.getDelay(TimeUnit.MILLISECONDS));
        helloFuture.cancel(true);

        taskScheduler.scheduleCron(() -> System.out.println("Hello new world"), "*/2 * * * * *");
        ThreadUtils.sleep(5000L);

        System.out.println("terminate!!!");
        taskScheduler.awaitTermination(5000L, TimeUnit.MILLISECONDS);
        taskScheduler.shutdown();
    }

    @Test
    void testScheduleDate() throws InterruptedException {
        TaskScheduler taskScheduler = new ThreadPoolTaskScheduler(1);
        taskScheduler.scheduleAtDate(() -> System.out.println("Hello world"), new Date());
        taskScheduler.awaitTermination(5000L, TimeUnit.MILLISECONDS);
        taskScheduler.shutdown();
    }
}