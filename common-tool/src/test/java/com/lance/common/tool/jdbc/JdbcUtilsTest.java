package com.lance.common.tool.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Lance
 * @since 2020/12/29
 */
public class JdbcUtilsTest {

    private String driverClassName = "";
    private String url = "";
    private String username = "";
    private String password = "";

    @Test
    public void test() {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, username, password);
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from t_data");

            String result = JdbcUtils.handle(rs, resultSet -> resultSet.getString("account"));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(stat);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Test
    public void testArrayListHandler() {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, username, password);
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from t_data");

            List<String> result = JdbcUtils.handle(rs, new ArrayListHandler<String>() {
                @Override
                public String handleRow(ResultSet rs) throws SQLException {
                    return rs.getString("account");
                }
            });
            result.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(stat);
            JdbcUtils.closeQuietly(conn);
        }
    }
}