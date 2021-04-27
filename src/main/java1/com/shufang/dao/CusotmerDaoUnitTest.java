package com.shufang.dao;

import com.shufang.entities.Customer;
import com.shufang.utils.JDBCUtilOfMine;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CusotmerDaoUnitTest {

    CustomerDaoImpl cust = new CustomerDaoImpl();

    @Test
    public void test1() throws SQLException, IOException, ClassNotFoundException {

        Connection conn = JDBCUtilOfMine.getConnection();
        Customer cust = this.cust.getById(conn, 1);

        System.out.println(cust);
        JDBCUtilOfMine.close(conn);
    }

    @Test
    public void test2() throws SQLException, IOException, ClassNotFoundException {

        Connection conn = JDBCUtilOfMine.getConnection();
        System.out.println(cust.getCustCount(conn));
        JDBCUtilOfMine.close(conn);
    }

    @Test
    public void test3() throws SQLException, IOException, ClassNotFoundException {

        Connection conn = JDBCUtilOfMine.getConnection();

        List<Customer> all = cust.getAll(conn);

        for (Customer customer : all) {
            System.out.println(customer);
        }
        JDBCUtilOfMine.close(conn);
    }

    @Test
    public void test4() throws SQLException, IOException, ClassNotFoundException {

        Connection conn = JDBCUtilOfMine.getConnection();

        cust.update(conn,new Customer(1,"舒放大佬","shufalone@126.com",null,null));

        JDBCUtilOfMine.close(conn);
    }






}
