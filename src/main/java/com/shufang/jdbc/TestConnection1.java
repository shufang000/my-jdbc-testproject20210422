package com.shufang.jdbc;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class TestConnection1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {


        InputStream in = TestConnection1.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties properties = new Properties();
        properties.load(in);

        //1、注册驱动
        Class.forName(properties.getProperty("driver"));

        //2、从配置文件获取到对应的连接信息
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        Connection connection = DriverManager.getConnection(url, username, password);


        System.out.println(connection);


//        ps.close();
        connection.close();
    }

}
