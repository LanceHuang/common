package com.lance.common.tool.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Utilities of time-format and so on.
 *
 * @author Lance
 * @since 2019/6/18 15:58
 */
public class TimeUtils {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

    public static long now() {
        return System.currentTimeMillis();
    }

    public static String format(Date date) {
        return sdf.format(date);
    }

    public static String format(long time) {
        return sdf.format(time);
    }

    public static void sleep(long time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
