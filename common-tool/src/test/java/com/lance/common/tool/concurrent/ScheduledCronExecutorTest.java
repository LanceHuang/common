package com.lance.common.tool.concurrent;

import com.lance.common.tool.ThreadUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lance
 * @since 2022/3/23
 */
class ScheduledCronExecutorTest {

    @Test
    void testScheduleCron() throws InterruptedException {
        ScheduledCronExecutor scheduledCronExecutor = new ScheduledCronExecutor(1);
        Future<?> helloFuture = scheduledCronExecutor.scheduleCron(() -> System.out.println("Hello world"), "*/1 * * * * *");
        ThreadUtils.sleep(5000L);
        helloFuture.cancel(true);

        scheduledCronExecutor.scheduleCron(() -> System.out.println("Hello new world"), "*/2 * * * * *");
        ThreadUtils.sleep(5000L);

        System.out.println("terminate!!!");
        scheduledCronExecutor.awaitTermination(5000L, TimeUnit.MILLISECONDS);
        scheduledCronExecutor.shutdown();
    }

}