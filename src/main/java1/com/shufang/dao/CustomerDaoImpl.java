package com.shufang.dao;


import com.shufang.entities.Customer;

import java.sql.Connection;
import java.util.List;

/**
 * connection对象在哪里创建就在哪里close，不要在方法俩面关闭参数conn
 */
public class CustomerDaoImpl extends BaseDao implements CustomerDao {
    public void insert(Connection conn, Customer cust) {
        String sql = "insert into customers(id,name,email) values(?,?,?)";
        commonUpdate(conn, sql, cust.getId(), cust.getName(), cust.getEmail());
    }

    public void deleteById(Connection conn, Integer id) {
        String sql = "delete from customers where id = ?";
        commonUpdate(conn, sql, id);
    }

    public void update(Connection conn, Customer cust) {
        String sql = "update customers set name = ? ,email = ? where id = ?";
        commonUpdate(conn, sql, cust.getName(), cust.getEmail(), cust.getId());
    }

    public Customer getById(Connection conn, Integer id) {
        String sql = "select * from customers where id = ?";
        Customer customer = getAInstance(conn, sql, Customer.class, id);
        return customer;
    }

    public List<Customer> getAll(Connection conn) {
        String sql = "select  * from customers where id >= ?";
        List<Customer> custs = getInstanceList(conn, sql, Customer.class, -1);
        return custs;
    }

    public Long getCustCount(Connection conn) {

        String sql = "select count(1) from customers where id >= ?";
        Long counts = (Long) getCountValue(conn, sql, -1);
        return counts;
    }

}
