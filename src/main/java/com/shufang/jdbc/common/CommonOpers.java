package com.shufang.jdbc.common;


import com.shufang.utils.MyJDBCUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * 在SQL中，有天然批量操作的的，在CRUD中的update和delete操作是天然的批量操作
 */
public class CommonOpers {


    /**
     * 通用的增删改方法
     *
     * @param sql
     * @param args
     */
    public static void testUpdate(String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;


        try {
            //1 首先获取连接
            conn = MyJDBCUtil.getConnection();
            //2 准备SQL语句

            //3 获取PreparedStatement
            ps = conn.prepareStatement(sql);
            //4 填充占位符

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //5 执行SQL
            ps.execute();

            //6 关闭资源
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    MyJDBCUtil.close(conn);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    /**
     * 通用的查询语句
     * @param sql
     * @param clazz
     * @param args
     * @param <T>
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static <T> T commonSelect(String sql, Class<T> clazz, Object... args) throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        T t = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // 1 获取连接
        conn = MyJDBCUtil.getConnection();
        // 2 准备SQL

        // 3 获取PS对象
        ps = conn.prepareStatement(sql);
        // 4 填充占位符

        for (int i = 0; i < args.length; i++) {
            ps.setObject(i+1,args[i]);
        }

        // 5 执行SQL获取ResultSet
         rs = ps.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // 6 给T赋值，并返回
        if(rs.next()){
            t = clazz.newInstance();

            for (int i = 0; i < columnCount; i++) {

                String columnLabel = metaData.getColumnLabel(i + 1);
                Object columnValue = rs.getObject(columnLabel);

                Field field = clazz.getDeclaredField(columnLabel);

                field.setAccessible(true);
                field.set(t,columnValue);
            }

            return t;
        }

        // 7 关闭资源
        rs.close();
        ps.close();
        MyJDBCUtil.close(conn);
        return null;
    }

}
