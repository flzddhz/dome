package com.file;

import java.io.*;

public class FileIO {

    public static void main(String[] args) {
        try {
//            String fliepath = "C:\\Users\\sluo\\Desktop\\Redis讲义.pptx";
//            String fliepath2 = "C:\\Users\\sluo\\Desktop\\Redis讲义1.pptx";
            String fliepath = "C:\\Users\\sluo\\Desktop\\1.txt";
            String fliepath2 = "C:\\Users\\sluo\\Desktop\\2.txt";
            String fliepath3 = "C:\\Users\\sluo\\Desktop\\3.txt";

            StringBuffer sb = new StringBuffer();
            byte[] bb = new byte[32];

            //字节输入流
            FileInputStream fis = new FileInputStream(fliepath);
            //字节输出流
            FileOutputStream fos = new FileOutputStream(fliepath2);
            Writer writer = new OutputStreamWriter(new FileOutputStream(fliepath3));
            //缓冲字节输入流
            BufferedInputStream bfis = new BufferedInputStream(fis);
            //缓冲字节输出流


            int i = 0;
            while ((i = fis.read(bb)) != -1) {
                fos.flush();
                fos.write(bb);
                for (byte b:bb) {
                    sb.append(b);
                    writer.append((char) b);
                }
                System.out.println(sb);
//                writer.write();
            }

//            while ((i = bfis.read(bb,i,2048)) != -1) {
//                fos.flush();
//                fos.write(bb);
//            }

            fis.close();
            fos.close();

            System.out.println(fis.toString());
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
