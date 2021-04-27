package com.shufang.utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * NOTE: 这是一个工具类，通常是用来获取连接，关闭资源的一个类
 * 可以关闭的资源有
 * Connection
 * PreparedStatement
 * ResultSets
 * <p>
 * 由于是工具类，所以提供的方法都是静态方法
 */
public class JDBCUtilOfMine {





    /**
     * 获取Druid的连接
     */
    private static DataSource druidSource;
    static {
        try {
            Properties properties = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);

            //给静态属性赋值
            druidSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getDruidConnection() throws SQLException {
        Connection conn = druidSource.getConnection();
        return conn;
    }





    //用来获取c3p0连接的连接池，不能放在方法体内，不然会OOM
    public static ComboPooledDataSource cpdsDataSource = new ComboPooledDataSource("helloC3p0");
    /**
     * 使用配置文件来获取连接配置
     *
     * @return a c3p0的connection
     * @throws SQLException
     */
    public static Connection getC3p0Connection() throws SQLException {
        Connection connection = cpdsDataSource.getConnection();
        return connection;
    }

    /**
     * 获取连接资源的一个方法
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        //1、首先将配置文件中的配置信息读取到
        Properties prop = new Properties();
        InputStream is = JDBCUtilOfMine.class.getClassLoader().getResourceAsStream("jdbc.properties");
        prop.load(is);

        //2 读取配置信息
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String driver = prop.getProperty("driver");

        //2 加载驱动
        Class.forName(driver);

        //3 获取连接
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }


    /**
     * 用来关闭资源的一个方法，依次关闭这些资源
     *
     * @param conn
     * @param ps
     * @param rs
     * @throws SQLException
     */
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {

        DbUtils.closeQuietly(rs);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(conn);
//        if (rs != null) {
//            rs.close();
//            System.out.println("ResultSet[" + rs + "]关闭成功～～");
//        }
//        if (ps != null) {
//            ps.close();
//            System.out.println("PreparedStatement[" + ps + "]关闭成功～～");
//        }
//        if (conn != null) {
//            conn.close();
//            System.out.println("Connection[" + conn + "]关闭成功～～");
//        }


    }

    /**
     * 方法的重载
     *
     * @param conn
     * @throws SQLException
     */
    public static void close(Connection conn) throws SQLException {
        close(conn, null, null);
    }

    public static void close(PreparedStatement ps) throws SQLException {
        close(null, ps, null);
    }

    public static void close(ResultSet rs) throws SQLException {
        close(null, null, rs);
    }


    @Test
    public void getcontest() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JDBCUtilOfMine.getConnection();
        System.out.println(connection);

        close(connection, null, null);
    }


}
