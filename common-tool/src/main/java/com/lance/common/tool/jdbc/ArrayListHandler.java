package com.lance.common.tool.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 列表处理器
 *
 * @author Lance
 * @since 2020/12/29
 */
public abstract class ArrayListHandler<T> implements ResultSetHandler<List<T>> {

    @Override
    public List<T> handle(ResultSet rs) throws SQLException {
        List<T> result = new ArrayList<>();
        while (rs.next()) {
            result.add(handleRow(rs));
        }
        return result;
    }

    /**
     * 处理一行结果
     */
    public abstract T handleRow(ResultSet rs) throws SQLException;
}
