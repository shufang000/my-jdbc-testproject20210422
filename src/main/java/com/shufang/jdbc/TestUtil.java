package com.shufang.jdbc;

import com.shufang.utils.MyJDBCUtil;

import java.io.IOException;
import java.sql.*;

public class TestUtil {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = MyJDBCUtil.getConnection();
        //String sql = "create table if not exists emp(id int ,name varchar(20))";

        String sql = "select * from emp where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setObject(1,1001);

        ResultSet rs = ps.executeQuery();

        rs.close();
        ps.close();
        MyJDBCUtil.close(conn);

        System.out.println("ok ,your statement has been executed successfully!A");
    }
}
