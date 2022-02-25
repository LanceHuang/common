package com.lance.common.tool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author Lance
 * @since 2022/2/25
 */
public class ThreadUtils {

    public static ThreadFactory nameThreadFactory(String prefix) {
        return new ThreadFactoryBuilder().setNameFormat(prefix + "-%d").build();
    }

    public static Thread nameThread(Runnable runnable, String name) {
        return new Thread(runnable, name);
    }

    public static void runThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static void sleep(long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(long timeout, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
