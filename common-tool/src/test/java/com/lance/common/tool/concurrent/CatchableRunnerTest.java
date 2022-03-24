package com.lance.common.tool.concurrent;

import com.lance.common.tool.ThreadUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Lance
 * @since 2022/3/23
 */
class CatchableRunnerTest {

    @Test
    void test() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            ThreadUtils.sleep(1000L);
            System.out.println("Run task");
            throw new RuntimeException("An exception");
        });
        executorService.awaitTermination(3000L, TimeUnit.MILLISECONDS);
        executorService.shutdown();
    }

    @Test
    void testCatchable() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new CatchableRunner(() -> {
            ThreadUtils.sleep(1000L);
            System.out.println("Run task");
            throw new RuntimeException("An exception");
        }, Throwable::printStackTrace));
        executorService.awaitTermination(3, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}