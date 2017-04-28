package com.alphabgammainc.nestfinder.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by davidhuang on 2017-04-26.
 */



public class StringManager {
    private final static int oneDay =86400;
    private final static int oneWeek=604800;
    private final static int oneMinute=3600;
    private final static int oneSecond=60;
    private static SimpleDateFormat formatter;
    /**
     * @param time = given a long timestamp in secodns convert the time into how many hours ago
     * ie 5 minutes ago
     * BUG if time surpasses 1 day it wil show 24hours + ago which is wrong we want to show 1 day ago
     */
    public static String time(Long time){
        SimpleDateFormat stringFormater = new SimpleDateFormat("hh:mm a", Locale.CANADA);
        String dateString = stringFormater.format(new Date(time * 1000L));

        return dateString;

    }

    /**
     * this function converts the time to hours
     * @param time
     * @return
     */
    public static String ConvertTimeString(Long time){
        Long End_time = (time % oneDay);

        return combine(End_time / oneMinute,(End_time % oneMinute) / oneSecond,End_time);

    }
    public static String setDays(Long time){
        formatter = new SimpleDateFormat("EEEE MMMM d");
        // this returns one day ahead of today.
        String date = formatter.format(new Date(time * 1000L));
        return date;
    }
    public static String Process(Long s,Long e){

        String start = ConvertTimeString(s);
        String end = ConvertTimeString(e);

        formatter = new SimpleDateFormat("MMMM d, yyyy");
        String dateString_begin = formatter.format(new Date(s * 1000L))+" "+start+"-"+end;
        return dateString_begin;
    }
    public static String combine(Long hour, Long minutes, Long time){
        String AMPM;
        if (time >= 43200) {
            AMPM = " PM";
            if(time>=46800)hour-=12;
        } else AMPM = " AM";
        return  hour.toString()+":"+minutes.toString()+AMPM;
    }
    public static String MonthDayTime(Long time){
        formatter = new SimpleDateFormat("MMMM d");
        String datetime = formatter.format(new Date(time * 1000L));
        return datetime;
    }
    public static String TimeAgo(Long time){
        long temp = (System.currentTimeMillis()/1000)-time;

        if(temp<60)return (temp+" Second(s) ago");
        else if((temp/60)<60)return(temp/60+" Minute(s) ago");
        else if((temp/60)/60<24) return((temp/60)/60+" Hour(s) ago");
        else if(temp/60/60/24<31)return((temp/60)/60/24+" Day(s) ago");
        else if(temp/60/60/24/30<13)return((temp/60)/60/24/30+" Month(s) ago");
        else return((temp/60)/60/24/30/12+" Year(s) ago");

    }

}
