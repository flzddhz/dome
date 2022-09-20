package com.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {

    public static void main(String[] args) throws ParseException {
        String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));
        timestamp = "2021-12-02 07:03:03";
//		t_oa_report_data.setCreatedate(timestamp.substring(0,10));               //记得修改录入时间逻辑   日期改为小于八点，日期减1
//		t_oa_report_data.setCreatetime(timestamp.substring(11,16));
        int hour = Integer.parseInt(timestamp.substring(11,13));
        String date = timestamp.substring(0,10);
        if(hour>=0 && hour<8){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = sdf.parse(timestamp);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.DATE, -1);
            Date dt1 = rightNow.getTime();
            date = sdf.format(dt1);
        }
        String classes = "A";//班次更具几点来判断 ，改成与手动输入的无关
        if(hour<8 || hour>=20){
            classes = "B";
        }
        System.out.println(timestamp);
        System.out.println(date);
        System.out.println(classes);
    }
}
