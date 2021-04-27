package com.shufang.connectionpool;

import com.shufang.dao.CustomerDaoImpl;
import com.shufang.utils.JDBCUtilOfMine;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DruidConnectionDemo {


    CustomerDaoImpl customerDao = new CustomerDaoImpl();

    @Test
    public void test1() throws SQLException {
        Connection conn = JDBCUtilOfMine.getDruidConnection();
        System.out.println(conn);

        System.out.println(customerDao.getById(conn, 1));

        JDBCUtilOfMine.close(conn);


    }
}
