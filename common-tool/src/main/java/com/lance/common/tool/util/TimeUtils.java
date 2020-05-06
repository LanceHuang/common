package com.lance.common.tool.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 时间工具类
 *
 * @author Lance
 */
public class TimeUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

    public static final long ONE_SECOND = 1000;
    public static final long ONE_MINUTE = 60 * ONE_SECOND;
    public static final long ONE_HOUR   = 60 * ONE_MINUTE;
    public static final long ONE_DAY    = 24 * ONE_HOUR;
    public static final long ONE_WEEK   = 7 * ONE_DAY;

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
