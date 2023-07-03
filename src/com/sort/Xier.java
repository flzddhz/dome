package com.sort;

import java.util.Arrays;
//希尔算法
public class Xier {

    public static void main(String[] args) {
        Xier xe = new Xier();
        int[] arrays = new int[] {1,5,2,3,6,9,4,0,1};
        xe.method1(arrays);
//        xe.method2(arrays);
        System.out.println(Arrays.toString(arrays));
    }

    public  void method1(int[] arrays){
        //实现增量的变化
        for(int gap = arrays.length / 2; gap > 0; gap /= 2) {
            System.out.println("gap = " + gap);
            for(int i = gap; i < arrays.length; i++) {
                System.out.println("i = " + i);
                for(int j = i - gap; j >= 0; j -= gap) {
                    if(arrays[j] > arrays[j + gap]) {
                        int temp = arrays[j];
                        arrays[j] = arrays[j + gap];
                        arrays[j + gap] = temp;
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    //半成品
    public void method2(int[] arrays){
        int[] gaps = new int[] {4,3,2,1};

        for (int i = 0;i < gaps.length;i++ ){
            for (int j = gaps[i];j < arrays.length;j++){
                for (int k = j - gaps[i];k > 0;k -= gaps[i]){
                    if(arrays[j- gaps[i]] > arrays[j]) {
                        int temp = arrays[j];
                        arrays[j] = arrays[j - gaps[i]];
                        arrays[j - gaps[i]] = temp;
                    }
                }
            }
        }
    }
}
