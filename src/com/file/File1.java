package com.file;

import java.io.File;

public class File1 {
    private static final String PATH = "C:\\Users\\sluo\\Desktop\\.xlsx";
    public static void main(String[] args) {
        File file1 = new File(PATH);
        System.out.println(file1.getName());
//        File file = new File(PATH);
//        if(file.isDirectory()){
//            System.out.println("存在该路径！");
//            String[] dirPaths = file.list();
//            System.out.println(dirPaths[0]);
//            System.out.println(file.getName());
//            System.out.println(file.getPath());
//        }
    }
}
