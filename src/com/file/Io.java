package com.file;

import java.io.File;

public class Io {
    public static void main(String[] args) {

    }

    public String create(String path,String file){
        try{
            File file1 = new File(path + File.separator + file);
            if(!file1.exists()){
                if (file1.createNewFile()){
                    return "创建目标文件成功!";
                }else{
                    return "创建目标文件失败!";
                }
            }else{
                return "文件已存在!";
            }
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
