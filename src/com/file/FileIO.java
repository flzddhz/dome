package com.file;

import org.apache.http.entity.mime.content.FileBody;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileIO {

    public static void main(String[] args) {
        try {
//            String filepath = "C:\\Users\\sluo\\Desktop\\Redis讲义.pptx";
//            String filepath2 = "C:\\Users\\sluo\\Desktop\\Redis讲义1.pptx";
            String filepath = "C:\\Users\\sluo\\Desktop\\1.txt";
            String filepath2 = "C:\\Users\\sluo\\Desktop\\2.txt";
            String filepath3 = "C:\\Users\\sluo\\Desktop\\3.txt";
//            String fileUrl = "http://oa.suntakpcb.com:8080/oa_file/2022/09/22/2618767841380354440?file_name='新建 Microsoft Excel 工作表.xlsx'";
//            HttpURLConnection httpUrl = (HttpURLConnection) new URL(fileUrl).openConnection();
//            httpUrl.connect();
//            InputStream inputStream = httpUrl.getInputStream();
//            System.out.println(file.getName());
            String file2 = "C:\\Users\\sluo\\Desktop\\test.msg";


            StringBuffer sb = new StringBuffer();
            byte[] bb = new byte[32];

            //字节输入流
//            FileInputStream fis = new FileInputStream(fileUrl);
            //字节输出流
            FileOutputStream fos = new FileOutputStream(file2);
            Writer writer = new OutputStreamWriter(new FileOutputStream(filepath3));
            //缓冲字节输入流
//            BufferedInputStream bfis = new BufferedInputStream(fis);
            //缓冲字节输出流

            FileBody fileBody = new FileBody(new File(filepath));

            int i = 0;
//            while ((i = inputStream.read(bb)) != -1) {
                fos.flush();
                fos.write(bb);
                for (byte b:bb) {
                    sb.append(b);
                    writer.append((char) b);
                }
                System.out.println(sb);
//                writer.write();
//            }

//            while ((i = bfis.read(bb,i,2048)) != -1) {
//                fos.flush();
//                fos.write(bb);
//            }

//            fis.close();
            fos.close();
//            inputStream.close();

//            System.out.println(fis.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int deleteFile(File file) throws IOException{
        if (file.exists()){
            file.delete();
        }
        return 1;
    }
}
