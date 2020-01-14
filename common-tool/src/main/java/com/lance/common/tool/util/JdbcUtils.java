package com.lance.common.tool.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Utilities of JDBC
 *
 * @author Lance
 * @since 2019/6/18 15:54
 */
public class JdbcUtils {

    public static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                // Do nothing
            }
        }
    }

}
