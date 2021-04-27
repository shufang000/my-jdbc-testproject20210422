package com.shufang.dao;

import com.shufang.entities.Customer;

import java.sql.Connection;
import java.util.List;

public interface CustomerDao {

    // 插入
    void insert(Connection conn, Customer cust);

    // 删除ById
    void deleteById(Connection conn, Integer id);

    // 按照给定的Customer修改
    void update(Connection conn, Customer cust);

    // 查找
    //按照id查找
    Customer getById(Connection conn, Integer id);

    //返回所有的记录
    List<Customer> getAll(Connection conn);

    //聚合操作类似于max、count
    Long getCustCount(Connection conn);

}
