package com.company;

public class Th extends Thread {

    @Override
    public void run() {
        System.out.println("Thread  run()" + Thread.currentThread());
        for (int i = 1; i <= 1000; i++) {
            if(i==200){
                try {
                    Thread.yield();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            System.out.println("Thread:" + i + "次");
        }
    }

//    @Override
//    public void start() {
//        System.out.println("Thread  start()");
//        super.start();
//    }
}
