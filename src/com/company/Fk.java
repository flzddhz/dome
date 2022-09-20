package com.company;

import java.util.Scanner;

public class Fk {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入(请输入单数！)：");
        int height = 0;
        try{
            height = scanner.nextInt();
        }catch (Exception e){
            System.out.println("请输入数字！");
            return;
        }
        if (height != 0 && height % 2 == 0){
            System.out.println("请输入单数");
            return;
        }
//        System.out.println("菱形高度是: " + height);
        int top = (int) height / 2 + 1;
//        System.out.println("头部高度是: " + top);
        int buttom =  height - top;
//        System.out.println("腿部高度是: " + buttom);
        for(int i = 1;i<=height;i++){
            if (i <= top){
                for (int j = 0;j < buttom - i + 1;j++){
                    System.out.print(" ");
                }
                for (int k = 0;k < i;k++){
                    System.out.print("* ");
                }
            }else{
                for (int j = 0;j < i- top;j++){
                    System.out.print(" ");
                }
                for (int k = 0;k < height - i + 1;k++){
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }
}
