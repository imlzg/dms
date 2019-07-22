package com.bank.dms.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DayUtil {
    public static int getMaxDay() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int maxDay = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                maxDay = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                maxDay = 30;
                break;
            default:
                maxDay = 28;
                break;
        }
        if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
            if(month == 2)
                maxDay++;
        }
        return maxDay;
    }
    
    public static int getWeek() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.get(Calendar.DAY_OF_WEEK)-1;
    }
    
    public static String getDates(){
        Calendar c = Calendar.getInstance();
        String year = c.get(Calendar.YEAR) + "";
        String month = c.get(Calendar.MONTH) + 1 + "";
        if(month != null && month.length() != 2){
            month = 0 + month;
        }
        return year+"-" + month + "-";
    }
    
    public static String getDate(){
        Calendar c = Calendar.getInstance();
        String year = c.get(Calendar.YEAR) + "";
        String month = c.get(Calendar.MONTH) + 1 + "";
        String date = c.get(Calendar.DATE) + "";
        if(month != null && month.length() != 2){
            month = 0 + month;
        }
        if(date != null && date.length() != 2){
            date = 0 + date;
        }
        return year + "-" + month + "-" + date;
    }
    
    public static String getHourDay(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY) > 12 ? "下午" : "上午";
    }
    
    public static Timestamp getDay(){
        return new Timestamp(System.currentTimeMillis());
    }
    
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        System.out.println(c.getTime());
    }
}
