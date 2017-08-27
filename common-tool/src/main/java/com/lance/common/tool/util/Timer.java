package com.lance.common.tool.util;

/**
 * Millisecond timing for test
 *
 * @author Lance
 * @date 2016/11/6
 */
public final class Timer {
    private static long start;
    private static long end;

    private Timer() {
    }

    public static void time() {
        start = end;
        end = System.currentTimeMillis();
    }

    /**
     * @return millisecond interval
     */
    public static long interval() {
        return end - start;
    }
}
