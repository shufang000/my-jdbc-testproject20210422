package com.shufang.dao;

import com.shufang.utils.JDBCUtilOfMine;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是一个通用的DAO类，通常需要被不同类型的Object的Dao继承
 * 类似于 CustomerDaoImpl implements  CustomerDao extends BaseDao{
 *     // do something you wanna do ~~
 * }
 */
public abstract class BaseDao {


    /**
     * 这是一个所有类（数据库所有表的）的通用的增删改的一个方法
     *
     * @param conn 连接
     * @param sql  执行的SQL
     * @param args 需要传入的参数，用来替换占位符`?`
     */
    public int commonUpdate(Connection conn, String sql, Object... args) {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            System.out.println("当前SQL：{" + sql + " }正在执行.......");
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtilOfMine.close(null, ps, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    /**
     * 根据参数获取一行记录，并返回一个ORM的Java对象实例
     *
     * @param conn  数据库连接
     * @param sql   准备执行的SQL
     * @param clazz 用来反射的对象，控制返回的类型
     * @param args  传入的参数
     * @param <T>   使用范型保证返回值的灵活性
     * @return
     */
    public <T> T getAInstance(Connection conn, String sql, Class<T> clazz, Object... args) {

        T t = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try {
            //获取预编译对象
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                //设置占位符参数
                ps.setObject(i + 1, args[i]);
            }

            //执行获取ResultSet实例
            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            if (rs.next()) {
                t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //获取每个列的identity
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //获取对象的每个属性
                    Field field = clazz.getDeclaredField(columnLabel);
                    //获取到具体的某个列值
                    Object object = rs.getObject(columnLabel);
                    field.setAccessible(true);
                    field.set(t, object);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtilOfMine.close(null, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    /**
     * 根据不同的条件获取一个包含N个对象的列表的通用方法
     *
     * @param conn
     * @param sql
     * @param clazz
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> getInstanceList(Connection conn, String sql, Class<T> clazz, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取预编译对象
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                //设置占位符参数
                ps.setObject(i + 1, args[i]);
            }

            //执行获取ResultSet实例
            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            //用来装返回对象的一个容器
            ArrayList<T> list = new ArrayList<T>();

            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //获取每个列的identity
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //获取对象的每个属性
                    Field field = clazz.getDeclaredField(columnLabel);
                    //获取到具体的某个列值
                    Object object = rs.getObject(columnLabel);

                    field.setAccessible(true);
                    field.set(t, object);

                }

                //添加对象到容器中
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtilOfMine.close(null, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    /**
     * 返回一个值，通常是用来实现SUM COUNT  MAX 等类型的操作
     *
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    public <E> E getCountValue(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //执行SQL查询
            rs = ps.executeQuery();

            if (rs.next()) {
                E object = (E) rs.getObject(1);
                return object;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                JDBCUtilOfMine.close(null, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
