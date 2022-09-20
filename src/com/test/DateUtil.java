package com.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final long ONE_DAY_INTERVAL = 86400000L;

    public static long getDelayTime(long targetTime,long interval) throws Exception{
        long delayTime = targetTime - System.currentTimeMillis();
        //delayTime = delayTime > 0 ? delayTime : delayTime + interval;
        while(true){
            if(delayTime <= 0){
                delayTime += interval;
            }else{
                break;
            }
        }
        return delayTime;
    }

    public static long getDelayTime(String milliTime,long interval) throws Exception{
        return getDelayTime(getTimeMillisByTime(milliTime),interval);
    }

    public static long getTimeMillisByTime(String time) throws Exception{
        String date = formatDate(new Date(),"yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date +" " + time).getTime();
    }

    public static String formatDate(Date date,String formatStr) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }
}
