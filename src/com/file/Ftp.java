package com.file;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import sun.net.TelnetInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Ftp {

//    private static String FTP_SERVER = "10.7.10.251";
//    private static String USER_NAME = "zhfqaftp01";
//    private static String USER_PWD = "Zhfqa%%2022";
//    private static String USER_PWD = "Zhfqa%%2022";
//    private static String FTP_SERVER = "192.168.0.122";
//    private static String USER_NAME = "client";
//    private static String USER_PWD = "client";
    private static String FTP_SERVER = "192.168.110.165";
    private static String USER_NAME = "dlftp";
    private static String USER_PWD = "Suntakdl61";

    public static void main(String[] args) throws IOException {
        try{
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(FTP_SERVER,21);
            ftpClient.login(USER_NAME,USER_PWD);
            OutputStream os = null;
            File file = new File("E:\\file");
            os = new FileOutputStream(file);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                System.out.println("FTP连接成功。");
                TelnetInputStream ftpIn = null;
                try {
                    String str = "\\FQA（出货报告）\\2022 新打印\\126 出货超过两周期需问客 ，周期先进先出，C-362是年周 ，阻抗要编号实测值对应 ，注意C-231\\100350233L0299B\\2022.06.22-2422\\220403256-1.1\\100350233L0299B.ppt";
                    boolean b = ftpClient.retrieveFile(new String(str.getBytes(),"ISO-8859-1"), os);
                    System.out.println(b);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
