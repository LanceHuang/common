package com.lance.common.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期工具类
 *
 * @author Lance
 */
public class DateUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final long ONE_SECOND = 1000;
    public static final long ONE_MINUTE = 60 * ONE_SECOND;
    public static final long ONE_HOUR   = 60 * ONE_MINUTE;
    public static final long ONE_DAY    = 24 * ONE_HOUR;
    public static final long ONE_WEEK   = 7 * ONE_DAY;


    /**
     * 将字符串解析成时间对象
     */
    public static Date parse(String dateStr) {
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Illegal date string: " + dateStr);
        }
    }

    /**
     * 将字符串按formatStr格式，解析成时间对象
     */
    public static Date parse(String dateStr, String formatStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Illegal date string: " + dateStr);
        }
    }

    /**
     * 将日期格式化成字符串
     */
    public static String format(Date date) {
        return sdf.format(date);
    }

    /**
     * 将时间戳格式化成字符串
     */
    public static String format(long time) {
        return sdf.format(time);
    }

    /**
     * 将日期按formatStr格式，转换成字符串
     */
    public static String format(Date date, String formatStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
        return simpleDateFormat.format(date);
    }

}
