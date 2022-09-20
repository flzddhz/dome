package com.jdbc;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

public class Ftp {

//    private static String FTP_SERVER = "10.7.10.251";
//    private static String USER_NAME = "zhfqaftp01";
//    private static String USER_PWD = "Zhfqa%%2022";

    //测试和深圳都用这个  测试用client登录
//    private static String FTP_SERVER = "192.168.0.122";
//    private static String USER_NAME = "client";
//    private static String USER_PWD = "client";

    //大连电子
    private static String FTP_SERVER = "192.168.110.158";
    private static String USER_NAME = "Drilling";
    private static String USER_PWD = "DrillingDrilling";

    public static void main(String[] args) throws IOException {
        try{
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(FTP_SERVER,21);
            ftpClient.login(USER_NAME,USER_PWD);

            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                System.out.println("FTP连接成功。");
                System.out.println(ftpClient.deleteFile("\\file bk\\1.txt"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
