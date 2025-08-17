package com.ruoyi.project.tool.gen.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static String getCurYearFirstDayStr(){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return year+"-01-01 00:00:00";
    }

    public static String getCurTimeStr(Date date,String pattern){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static Date geTimeFromStr(String timeStr,String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(timeStr);
    }

    //获取当前的天
    public static Integer getCurDayOfMonth(){
        Calendar calendar=Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    //获取当前时间
    public static Integer getCurHour(){
        Calendar calendar=Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    //获取之前几天的日期str yyyy-MM-dd
    public static String getLastServerlDayStr(Date date,Integer lastServerlDays){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1*lastServerlDays);
        String year=""+calendar.get(Calendar.YEAR);
        int monthInt=calendar.get(Calendar.MONTH)+1;
        String month=monthInt<10?("0"+monthInt):(""+monthInt);
        String day=calendar.get(Calendar.DATE)<10?("0"+calendar.get(Calendar.DATE)):(""+calendar.get(Calendar.DATE));
        return year+"-"+month+"-"+day;
    }

    //获取之前几天的日期str yyyy-MM-dd
    public static Date getLastServerlDay(Date date,Integer lastServerlDays){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1*lastServerlDays);
        return calendar.getTime();
    }

    //14位时间
    public static Date getDateFromFourteenLengthTimeStr(String timeStr){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String year=timeStr.substring(0,4);
        String month=timeStr.substring(4,6);
        String day=timeStr.substring(6,8);
        String hour=timeStr.substring(8,10);
        String min=timeStr.substring(10,12);
        String sec=timeStr.substring(12);
        try {
            return df.parse(year+"-"+month+"-"+day+" "+hour+":"+min+":"+sec);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取对应天的0点
    public static Date getTodayZeroDate(Date date) throws ParseException {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DATE);
        String zeroDateStr=year+"-"+month+"-"+day+" 00:00:00";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.parse(zeroDateStr);
    }

    //获取对应天的24点
    public static Date getTargetDay24HourDate(Date date) throws ParseException {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DATE);
        String tempDateStr=year+"-"+month+"-"+day+" 23:59:59";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.parse(tempDateStr);
    }

    public static String getUsedTimeStr(Date startDate,Date endDate){
        long spendTimeMills=endDate.getTime()-startDate.getTime();
        long spendTimeSeconds=spendTimeMills/1000;
        long h=spendTimeSeconds/3600;
        long m=(spendTimeSeconds%3600)/60;
        long s=spendTimeSeconds%60;
        StringBuilder sb=new StringBuilder();
        if(h>0){
            sb.append(h).append("小时");
        }
        if(m>0){
            sb.append(m).append("分");
        }
        if(s>0){
            sb.append(s).append("秒");
        }
        String str=sb.toString();
        if(str.length()==0){
            return spendTimeMills+"ms";
        }
        return str;
    }

    //获取之前几天的日期str yyyy-MM-dd
    public static String getLastServerlDayStr2(Date date,Integer lastServerlDays){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1*lastServerlDays);
        String year=""+calendar.get(Calendar.YEAR);
        int monthInt=calendar.get(Calendar.MONTH)+1;
        String month=monthInt<10?("0"+monthInt):(""+monthInt);
        String day=calendar.get(Calendar.DATE)<10?("0"+calendar.get(Calendar.DATE)):(""+calendar.get(Calendar.DATE));
        return year+month+day;
    }
}
