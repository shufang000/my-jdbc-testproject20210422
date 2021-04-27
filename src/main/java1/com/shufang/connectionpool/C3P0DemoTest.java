package com.shufang.connectionpool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import com.shufang.utils.JDBCUtilOfMine;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0DemoTest {

    @Test
    public void testByHardCode() throws PropertyVetoException, SQLException {

        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
        cpds.setUser("root");
        cpds.setPassword("888888");

        Connection conn = cpds.getConnection();

        System.out.println(conn);

        conn.close();

        DataSources.destroy(cpds);
    }

    @Test
    public void testByConfigXML() throws SQLException {
        System.out.println(JDBCUtilOfMine.getC3p0Connection());
    }
}
