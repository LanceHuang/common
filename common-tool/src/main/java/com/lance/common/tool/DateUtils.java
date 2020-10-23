package com.lance.common.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utilities of date
 *
 * @author Lance
 */
public class DateUtils {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-DD HH:mm:ss";

    private static final SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

    public static Date parse(String dateStr) {
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Illegal date string: " + dateStr);
        }
    }

    public static Date parse(String dateStr, String formatStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Illegal date string: " + dateStr);
        }
    }

    public static String format(Date date) {
        return sdf.format(date);
    }

    public static String format(Date date, String formatStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
        return simpleDateFormat.format(date);
    }

}
