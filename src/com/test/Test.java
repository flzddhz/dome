package com.test;


public class Test {

    public static void main(String[] args) throws Exception {
        long delay = DateUtil.getDelayTime("08:45:00", DateUtil.ONE_DAY_INTERVAL);
        System.out.println(delay);
        Double d = 0.324432432;
        System.out.println(d*100);
        System.out.println((int)(d*100) +"");
        System.out.println(( ( (int)(d*100) )/100) );
        String str = (534 / 1000.0 + "");
        System.out.println(str);
    }
}

