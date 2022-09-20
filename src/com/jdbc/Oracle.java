package com.jdbc;

import oracle.jdbc.driver.OracleDriver;

import java.sql.*;

public class Oracle {

//    static final String USER = "report";
//    static final String PASSWORD = "report";
//    static final String USER = "appsclone";
//    static final String PASSWORD = "appsclone";

    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
//    static final String DB_URL = "jdbc:oracle:thin:@10.1.100.46:1521:rptdb";
//    static final String DB_URL = "jdbc:oracle:thin:@10.1.100.152:1526:test2";
//    static final String DB_URL = "jdbc:oracle:thin:@cderpdev2:1527:test3";

    /**
     * OA
     */
//    static final String USER = "v3xuser";
//    static final String PASSWORD = "v3xuser";
    //    static final String DB_URL = "jdbc:oracle:thin:@10.1.100.170:1521/OAPROD";

    /**
     * ERP-TEST
     */
//    static final String USER = "apps";
//    static final String PASSWORD = "apps";
//    static final String DB_URL = "jdbc:oracle:thin:@jmerp.suntakpcb.com:1526/TEST2";

    /**
     * ERP
     */
//    static final String DB_URL = "jdbc:oracle:thin:@(description=(address_list=(address=(host=10.1.100.31)(protocol=tcp)(port=1521))(address=(host=10.1.100.32)(protocol=tcp)(port=1521))(load_balance=yes)(failover=yes))(connect_data=(service_name=PROD)))";
    static final String DB_URL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS_LIST =(ADDRESS = (PROTOCOL = tcp)(HOST = 10.1.100.16)(PORT = 1527)) )(CONNECT_DATA = (SERVICE_NAME = TEST3) ))";
    static final String USER = "apps";
//    static final String PASSWORD = "Suntakpcb2017";
    static final String PASSWORD = "apps";

    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            String sql = "select 1,2,3 from dual";
            st = conn.prepareCall(sql);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("执行失败");
        } finally {
            System.out.println("执行完成");
        }
    }
}
