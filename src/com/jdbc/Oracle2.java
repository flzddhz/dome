package com.jdbc;

import oracle.jdbc.OracleType;
import oracle.jdbc.driver.OracleDriver;

import java.sql.*;

public class Oracle2 {

//    static final String USER = "report";
//    static final String PASSWORD = "report";
//    static final String USER = "appsclone";
//    static final String PASSWORD = "appsclone";

    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
//    static final String DB_URL = "jdbc:oracle:thin:@10.1.100.46:1521:rptdb";
//    static final String DB_URL = "jdbc:oracle:thin:@10.1.100.152:1526:test2";
//    static final String DB_URL = "jdbc:oracle:thin:@cderpdev2:1527:test3";

    //    static final String USER = "v3xuser";
//    static final String PASSWORD = "v3xuser";
    //    static final String DB_URL = "jdbc:oracle:thin:@10.1.100.170:1521/OAPROD";

//    static final String USER = "apps";
//    static final String PASSWORD = "apps";
//    static final String DB_URL = "jdbc:oracle:thin:@jmerp.suntakpcb.com:1526/TEST2";

    static final String USER = "tms2erp";
    static final String PASSWORD = "tms2erp";
    static final String DB_URL = "jdbc:oracle:thin:@(description=(address_list=(address=(host=10.1.100.31)(protocol=tcp)(port=1521))(address=(host=10.1.100.32)(protocol=tcp)(port=1521))(load_balance=yes)(failover=yes))(connect_data=(service_name=PROD)))";

    public static void main(String[] args){
        Connection conn = null;
        CallableStatement st = null;
        PreparedStatement pt = null;
        Integer line_id = 20726654;
        Integer apiCode = null;
        String codeMsg = null;
        ResultSet rs = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
//            String sql = "select 1,2,3 from dual";
            String sql = "call CUX_TMS_INTERFACE_PKG.ebs_interface_hk_deliver(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            st = conn.prepareCall(sql);
            st.registerOutParameter(1, OracleType.VARCHAR2);
            st.registerOutParameter(2,OracleType.NUMBER);
            st.setInt(3,line_id);
            st.setInt(4,line_id);
            st.setString(5,"");
            st.setString(6,"14040380");
            st.setInt(7,5488);
            st.setInt(8,1);
            st.setString(9,"Local仓库");
            st.setString(10,"");
            st.setString(11,"");
            st.setString(12,"");
            st.setString(13,"");
            st.setString(14,"Hermann-L?ns-Strasse 40-44 75389 Neuweiler Germany,");
            st.setString(15,"MOS-Electronic GmbH");
            st.setString(16,"");
            st.setString(17,"HK20220824");
            st.setString(18,"FOBHK");
            st.setString(19,"MOS-Electronic GmbH");
            st.setString(20,"香港");
            st.setString(21,"德国");
            st.setString(22,"2022-8-24  11:59:59");
            st.setString(23,"N");
            st.execute();
            System.out.println(st.getString(1));
            System.out.println(st.getInt(2));
//                System.out.println(rs.getString(3));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("执行失败");
        }finally {
            System.out.println("执行完成");
        }
    }
}
