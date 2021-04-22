package com.shufang.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class MyJDBCUtil {

    /**
     * @return 获取连接资源
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties prop = new Properties();
        InputStream in = MyJDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        prop.load(in); //加载配置文件中的配置属性信息

        //获取到我们需要的信息
        String driver = prop.getProperty("driver");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String url = prop.getProperty("url");

        //加载驱动
        Class.forName(driver);

        //获取连接
        Connection conn = DriverManager.getConnection(url, username, password);

        if (in != null) {
            in.close();
        }
        //返回连接
        return conn;
    }


    /**
     * 关闭连接
     *
     * @param conn
     * @throws SQLException
     */
    public static void close(Connection conn) throws SQLException {
        close(conn, null);
    }

    public static void close(PreparedStatement ps) throws SQLException {
        close(null, ps);
    }

    public static void close(Connection conn, PreparedStatement ps) throws SQLException {
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    }


}
