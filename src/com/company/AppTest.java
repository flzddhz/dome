package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppTest {
    public static void main(String[] args) throws ParseException {
        String str1 = "2021-11-30 10:00:00";
        String str2 = "2021-12-01 07:59:00";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(str2);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DATE, -1);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);

        System.out.println(reStr);
    }
}
