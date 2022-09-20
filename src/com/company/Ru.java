package com.company;

public class Ru extends Thread implements Runnable{
    @Override
    public void run() {
        System.out.println("Runnable  run()" + Thread.currentThread());
        for (int i = 1;i <= 1000;i++){
            System.out.println("Runnable:" + i + "次");
        }
    }
}
