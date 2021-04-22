package com.shufang.jdbc;

import com.shufang.beans.Emp;
import com.shufang.jdbc.common.CommonOpers;

import java.io.IOException;
import java.sql.SQLException;

public class TestCommonOpers {
    public static void main(String[] args) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        String sql = "insert into emp(id,name) values(?,?)";
//        CommonUpdate.testUpdate(sql,1001,"shufang");

        sql = "delete  from emp where id = ?";
//        CommonUpdate.testUpdate(sql,1001);



        sql = "update emp set name = ? where id = ?";

//        CommonOpers.testUpdate(sql,"shufang",1002);
        System.out.println("yes , it's a great thing you have done~~ ");




        sql = "select id,name from emp where id = ? ";

        Emp emp = CommonOpers.commonSelect(sql, Emp.class, 1002);

        System.out.println("最终的返回的emp = "+emp);


    }
}
