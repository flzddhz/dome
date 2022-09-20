package com.company;

import java.io.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {
//        // write your code here
//        Th th = new Th();
//        Ru ru = new Ru();
//
//        Thread t1 = new Th();
//        Thread t2 = new Ru();
////        Thread t3 = new Ru();
//
//        t1.start();
//        t2.start();
////        t3.start();

//        String a = null;
//        Object o = a;
//        Objects os = (Objects) (Object) a;
//        System.out.println(Objects.equals(o,null));
//        System.out.println(a.equals(""));
//
//        SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar calendar = Calendar.getInstance();
//        System.out.println("month" + calendar.get(calendar.MONTH) );
//        System.out.println("day" + calendar.get(calendar.DATE));
//
//        System.out.println("ddd" + File.separator + "fff");
//        String createdate = "2022-04-27 08:59:00";
//        calendar.set(Calendar.YEAR, Integer.parseInt(createdate.substring(0,4)));
//        calendar.set(Calendar.MONTH, Integer.parseInt(createdate.substring(5,7))-1);
//        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(createdate.substring(8,10)));
//        int hour  = Integer.parseInt(createdate.substring(11,13));
//        if(hour>=0 && hour<8){
//            calendar.add(Calendar.DATE, -1);
//        }
//        String classes = "白班";//班次
//        if(hour<8 || hour>=20){
//            classes = "夜班";
//        }
//        String dateAccordingTo = matter.format(calendar.getTime());//生产日期
//
//        System.out.println(classes);
//        System.out.println(dateAccordingTo);

        //不同编码的空格问题
//        System.out.println(" ".hashCode());
//        System.out.println(" ".hashCode());
//        System.out.println(" ".hashCode());
//        System.out.println(" ".hashCode());
//        System.out.println(" ".hashCode());
//        System.out.println(" ".hashCode());
//        System.out.println(" ".hashCode());
//        System.out.println(" ".hashCode());

//        try {
//            Thread.sleep(2000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        System.out.println(" ".hashCode());
//        System.out.println("　".hashCode());
//        System.out.println(" ".hashCode());
//        System.out.println("  ".hashCode());
//        System.out.println("\uE640".hashCode());

//        OutputStream out = new FileOutputStream("aaaa");
//        out.write(null);

//        String str1 = new String("aaaaaaaaaa");
//        String re = "1";
//        String rel = "";
//        System.out.println(rel.replace(re,str1));

        //int long 最大值
//        Double d = Double.valueOf(String.valueOf(null));
//        int i = 2147483647;
//        long l = 9223372036854775807l;
//        long v = 4294967295l;
//        long b = 2147483648l;
//        System.out.println((int)b);
////        System.out.println(l);
//        System.out.println((int)l == (int)v);

//        String s = "211100619-19.1";
//        System.out.println(s.substring(s.indexOf("-")+1));

        String ckg = "lfdsjaf*fdjslk*12323";
        System.out.println(ckg.substring(0,ckg.indexOf("*")));
        System.out.println(ckg.substring(ckg.indexOf("*")+1,ckg.lastIndexOf("*")));
        System.out.println(ckg.substring(ckg.lastIndexOf("*")+1));
    }
}
