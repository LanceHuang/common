package com.lance.common.tool.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 结果集处理器
 *
 * @param <T> 返回值类型
 * @author Lance
 * @since 2020/12/29
 */
public interface ResultSetHandler<T> {

    /**
     * 处理结果集
     *
     * @param rs 结果集
     */
    T handle(ResultSet rs) throws SQLException;
}
