package com.shufang.transaction;


import com.shufang.utils.MyJDBCUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * > 1 DDL操作一旦执行就会自动提交事务
 * > 2 DML操作在默认情况下一旦执行就会提交事务
 * > 假如想取消DML操作的自动事务提交，我们可以通过conn.setAutoCommit(false)来处理，这个操作对DDL无用
 * > 3 默认在关闭连接的时候会自动提交事务
 */
public class TransactionDemo {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //1 获取连接
            conn = MyJDBCUtil.getConnection();
            //2 关闭自动提交
            conn.setAutoCommit(false);

            //3 执行SQL,手动提交事务,
            {
            String sql1 = "update deal set balance = balance - 100 where id = ?";
            ps = conn.prepareStatement(sql1);
            ps.setObject(1,"A");
            ps.execute();

            //模拟网络异常，这里用的是一个数据算术的异常，分母为0
//            System.out.println(10/0);

            String sql2 = "update deal set balance = balance + 100 where id = ?";
            ps = conn.prepareStatement(sql2);
            ps.setObject(1,"B");
            ps.execute();
            } //这个花括号中的就是一个原子的事务操作，与其他的事务操作是相互隔离的，A-100，B+100

            //4 手动提交
            conn.commit();

        } catch (Exception e) {
            //5 发现异常回滚事务
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {

            try {
                //6 最终开启自动提交事务
                conn.setAutoCommit(true);
                MyJDBCUtil.close(conn,ps);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
