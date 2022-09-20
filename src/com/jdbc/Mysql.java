package com.jdbc;

import java.sql.*;
import java.util.logging.Logger;

public class Mysql {

    static final String USER = "root";
    static final String PASSWORD = "123456";

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://111.229.178.45:3306/mysql?test=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    public static void main(String[] args){
        Logger logger = Logger.getLogger(Mysql.class.getName());

        Connection conn = null;
        Statement st = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            String sql = "select * from test where id = 1";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
                logger.info(rs.getString(4));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("执行完成");
        }
    }
}
