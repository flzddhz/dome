package com.file;

import java.io.File;

public class File2 {

    public static void main(String[] args) {
        String path = "D:\\file";
        File2 file2 = new File2();
        file2.fenlei(path, 1);
    }

    public void fenlei(String path, int level) {
        File file = new File(path);
        String tree = "";

        if (file.isDirectory()) {
            for (int i = 0; i < level; i++) {
                tree += "    ";
            }
            if (level == 1) {
                System.out.println(file.getName());
            }
            String[] list = file.list();
            for (String s : list) {
                File file1 = new File(path + File.separator + s);
                if (file1.isDirectory()) {
                    System.out.println(tree + file1.getName());
                    fenlei(file1.getPath(), (level + 1));
                }
                if (file1.isFile()) {
                    System.out.println(tree + file1.getName());
                }
            }

        }
    }
}
