package com.lance.common.tool.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utilities of date
 *
 * @author Lance
 * @since 2020/1/14 17:11
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

}
