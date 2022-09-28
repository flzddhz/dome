package com.jdbc;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class OracleIO {

    static final String USER = "apps";
    static final String PASSWORD = "apps";
    static final String DB_URL = "jdbc:oracle:thin:@jmerp.suntakpcb.com:1526/TEST2";

    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";

    public static void main(String[] args) throws SQLException {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        InputStream fis = null;
        BufferedInputStream bis = null;
        Connection conn = null;
        byte[] bt = new byte[1024];

        Map<String,Object> map = OracleIO.findPicture();
        Blob fileData = (Blob) map.get("fileData");
        String fileName = (String) map.get("fileName");
        System.out.println(fileData.length());
        //直接保存到桌面
        String folderPath = "C:\\Users\\sluo\\Desktop\\";
        File file = new File(folderPath + fileName);
        try {
            fis = fileData.getBinaryStream();
            bis = new BufferedInputStream(fis);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            while (bis.read(bt) != -1){
                bos.write(bt);
            }
            bos.flush();
            bos.close();
            conn = (Connection) map.get("conn");
            if (conn!=null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static Map<String,Object> findPicture(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        Map<String,Object> map = new Hashtable<>();
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            String sql = "select fl.file_data,fl.file_Name,fl.program_tag from fnd_lobs fl where fl.file_id = 9968431";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()){
                Blob fileData = rs.getBlob("file_data");
                String fileName = rs.getString("file_name");
                String fileName1 = rs.getString("program_tag");
                fileName.replace("/",fileName1==null?"":fileName1);
                fileName.replace("/",fileName1);  //NullPointerException
//                fileName1.replace("/","-");       //NullPointerException
                System.out.println(fileName1==null);
                System.out.println(fileName1=="");
                System.out.println("".equals(fileName1));
                map.put("fileData",fileData);
                map.put("fileName",fileName);
                map.put("conn",conn);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (rs!=null){
                    rs.close();
                }
                if (st!=null){
                    st.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }
}
