package com.file;

import java.io.File;

public class File1 {
    private static final String PATH = "\\\\192.168.110.165\\1-fqa出货报告\\FQA（出货报告）\\2022 新打印\\3785  终端名称以ACTIA  开头的,COC 中CTI 要填板料实际的CTI值    终端 ISKRAEMECO, d.d  要求不同周期的板子要分开提供COC 报告，不能在一份COC 报告中写多个周期的报告";
    public static void main(String[] args) {
        File file1 = new File("/home/applprod/OZ_TEMP/.zip");
        System.out.println(file1.getName());
        File file = new File(PATH);
        if(file.isDirectory()){
            System.out.println("存在该路径！");
            String[] dirPaths = file.list();
            System.out.println(dirPaths[0]);
            System.out.println(file.getName());
            System.out.println(file.getPath());
        }
    }
}
