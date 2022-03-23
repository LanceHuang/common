package com.lance.common.tool.concurrent;

import com.lance.common.tool.ThreadUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Lance
 * @since 2022/3/23
 */
class CronScheduledThreadPoolExecutorTest {

    @Test
    void testScheduleCron() throws InterruptedException {
        CronScheduledExecutorService cronScheduledExecutorService = new CronScheduledThreadPoolExecutor(1);
        ScheduledFuture<?> helloFuture = cronScheduledExecutorService.scheduleCron(() -> System.out.println("Hello world"), "*/1 * * * * *");
        ThreadUtils.sleep(5000L);
        System.out.println("Hello delay: " + helloFuture.getDelay(TimeUnit.MILLISECONDS));
        helloFuture.cancel(true);

        cronScheduledExecutorService.scheduleCron(() -> System.out.println("Hello new world"), "*/2 * * * * *");
        ThreadUtils.sleep(5000L);

        System.out.println("terminate!!!");
        cronScheduledExecutorService.awaitTermination(5000L, TimeUnit.MILLISECONDS);
        cronScheduledExecutorService.shutdown();
    }

}