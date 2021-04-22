package com.shufang.batch;

import com.shufang.utils.MyJDBCUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchInsert {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {



        /**
         * 攒SQL ps
         *  addBatch()
         *  executeBatch()
         *  clearBatch()
         */


        Connection conn = MyJDBCUtil.getConnection();
        String sql = "insert into goods(name) values(?)";
        PreparedStatement ps = conn.prepareStatement(sql);



        //统一执行 统一提交，当前先关闭自动提交事务
        conn.setAutoCommit(false);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {

            ps.setObject(1, "name_" + i+1);
            //1  攒SQL
            ps.addBatch();

            if (i % 500 == 0) {
                //2 批量执行
                ps.executeBatch();
                //3 清空缓存
                ps.clearBatch();
            }

            if (i == 999999) {
                ps.executeBatch();
                ps.clearBatch();
            }

        }

        // 执行完了之后，统一提交事务，这是一种优化
        conn.commit();

        long endTime = System.currentTimeMillis();

        System.out.println("最终耗时为" + (endTime - startTime) + "ms");


        ps.close();
        conn.close();

    }
}
