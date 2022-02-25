package com.lance.common.tool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lance
 * @since 2022/2/25
 */
class ThreadUtilsTest {

    @Test
    void test() throws InterruptedException {
        ThreadFactory threadFactory = ThreadUtils.nameThreadFactory("test");
        Thread t = threadFactory.newThread(() -> System.out.println("Hello world"));
        t.start();
        t.join();
    }
}