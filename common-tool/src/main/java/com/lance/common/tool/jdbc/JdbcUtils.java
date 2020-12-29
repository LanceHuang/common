package com.lance.common.tool.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库操作工具
 *
 * @author Lance
 * @since 2020/12/29
 */
public class JdbcUtils {

    /**
     * 释放资源
     */
    public static void closeQuietly(AutoCloseable autoCloseable) {
        if (autoCloseable == null) {
            return;
        }
        try {
            autoCloseable.close();
        } catch (Exception e) {
            // do nothing
        }
    }

    /**
     * 处理结果集
     *
     * @param rs      结果集
     * @param handler 结果集处理器
     * @param <T>     返回值类型
     */
    public static <T> T handle(ResultSet rs, ResultSetHandler<T> handler) throws SQLException {
        if (handler == null) {
            return null;
        }
        return handler.handle(rs);
    }
}
