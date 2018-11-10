package com.rbu.lamphouse.utils;

import android.annotation.SuppressLint;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/11 15:29
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class DataUtils {

    private static int mYear;
    private static int mMonth;
    private static int mDay;
    private static int mWay;
    private static int mHour;
    private static int mMinute;
    private static int mSecond;

    @SuppressLint("WrongConstant")
    public static String StringData(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getDefault());
        mYear = c.get(Calendar.YEAR); // 获取当前年份
        mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码
        mWay = c.get(Calendar.DAY_OF_WEEK) - 1;//获取当前星期（0-6） 0星期天
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mSecond = c.get(Calendar.SECOND);

        return mYear + "年" + mMonth + "月" + mDay+"日"+"/星期"+mWay + " /" +mHour + "时" + mMinute + "分" + mSecond + "秒";
    }

    @SuppressLint("WrongConstant")
    public static int getYear() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getDefault());
        mYear = c.get(Calendar.YEAR); // 获取当前年份
        return mYear;
    }



    @SuppressLint("WrongConstant")
    public static int getMonth() {
        final  Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getDefault());
        mMonth = c.get(Calendar.MONTH) + 1;
        return mMonth;
    }

    @SuppressLint("WrongConstant")
    public static int getDay () {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getDefault());
        mDay = c.get(Calendar.DAY_OF_MONTH);
        return mDay;
    }

    @SuppressLint("WrongConstant")
    public static int getHour () {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getDefault());
        mHour = c.get(Calendar.HOUR_OF_DAY);
        return mHour;
    }

    @SuppressLint("WrongConstant")
    public static int getMinute () {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getDefault());
        mMinute = c.get(Calendar.MINUTE);
        return mMinute;
    }

    @SuppressLint("WrongConstant")
    public static int getSecond () {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getDefault());
        mSecond = c.get(Calendar.SECOND);
        return mSecond;
    }

    @SuppressLint("WrongConstant")
    public static int getWay() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getDefault());
        mWay = c.get(Calendar.DAY_OF_WEEK) - 1;//获取当前星期（0-6） 0星期天
        return mWay;
    }

    public static int getHourFormString(String data) {
    String[] datas = data.split(":");
    return Integer.valueOf(datas[0]);
}

    public static int getMinuteFromString(String data) {
        String[] datas = data.split(":");
        return Integer.valueOf(datas[1]);
    }

    public static int getSecondFromString(String data) {
        String[] datas = data.split(":");
        return Integer.valueOf(datas[2]);
    }
}
