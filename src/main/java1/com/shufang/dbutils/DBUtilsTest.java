package com.shufang.dbutils;

import com.shufang.entities.Customer;
import com.shufang.utils.JDBCUtilOfMine;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 用来测试Apache的DBUtils类的使用，非常的方便
 */
public class DBUtilsTest {


    /**
     * 返回一个对象，可以使用BeanHandler，也可以使用MapHandler
     * 返回多个对象，可以使用BeanListHandler，也可以使用MapListHandler
     * @throws SQLException
     */
    @Test
    public void  test1() throws SQLException {

        //获取QueryRunner对象，用来query、update
        QueryRunner queryRunner = new QueryRunner();

        //获取连接
        Connection conn = JDBCUtilOfMine.getDruidConnection();

        //1、BeanHandler
        String sql = "select id,name,email from customers where id = ?";
        BeanHandler<Customer> customerBeanHandler = new BeanHandler<Customer>(Customer.class);


        MapHandler mapHandler = new MapHandler();
        Map<String, Object> query = queryRunner.query(conn, sql, mapHandler, 1);
        System.out.println(query);
        System.out.println("---------1-----------");

        //查询
        Customer cust = queryRunner.query(conn, sql, customerBeanHandler, 1);
        System.out.println(cust);

        System.out.println("---------2----------");
        sql = "select * from customers where id >= ?";
        //2、BeanListHandler
        BeanListHandler<Customer> customerBeanListHandler = new BeanListHandler<Customer>(Customer.class);
        List<Customer> custs = queryRunner.query(conn, sql, customerBeanListHandler, 1);

        sql = "insert into customers(name,email) values(?,?)";
        int hello = queryRunner.update(conn, sql, "hello", "hello@gmail.com");

        System.out.println("插入了 ===" + hello + " 行记录");


        // 3、关闭资源
        custs.forEach(System.out::println);
        DbUtils.close(conn);

    }

    /**
     * 出了返回数据库中的对象，还可以使用ScalarHandler来返回count之后的衍生字段，还可以求sum、max等等
     * @throws SQLException
     */
    @Test
    public void test2() throws SQLException {
        //获取QueryRunner对象，用来query、update
        QueryRunner queryRunner = new QueryRunner();

        //获取连接
        Connection conn = JDBCUtilOfMine.getDruidConnection();

        ScalarHandler<Long> longScalarHandler = new ScalarHandler<>();

        String sql = "select count(1) as one from customers";
        Long query = queryRunner.query(conn, sql, longScalarHandler);
        System.out.println(query);


        //DbUtils还提供了关闭资源的方法，我们可以在JDBCUtilOfMine中使用

        DbUtils.close(conn);

    }
}
