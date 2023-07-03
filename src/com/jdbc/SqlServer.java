package com.jdbc;

import java.sql.*;

public class SqlServer {

    static final String USER = "ebs_user1";
    static final String PASSWORD = "abc";
    static final String DB_URL = "jdbc:sqlserver://192.168.0.222\\\\MSSQLSERVER1:1444;databaseName=Suntak0919;";
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static void main(String[] args) {
        Connection conn;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement stat = conn.createStatement();//创建一个 Statement 对象来将 SQL 语句发送到数据库。
            ResultSet resultSet=stat.executeQuery("select * from Cux_Quo_Pdf");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("CustAb"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
