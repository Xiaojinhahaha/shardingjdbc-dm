package com.example.sharding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 类描述：ShardingJDBC分片时间算法；
 * @author xiaoj
 */
public class ShardingAlgorithmDateUtil {

    /**
     * 根据输入日期返回年月  202108
     *
     * @param millisecond
     * @return
     */
    public static String getYearJoinMonthByMillisecond(long millisecond) {
        SimpleDateFormat yearJoinMonthFormat = new SimpleDateFormat("yyyyMM");
        return yearJoinMonthFormat.format(new Date(millisecond));
    }

    /**
     * 根据输入日期返回年  2021
     *
     * @param millisecond
     * @return
     */
    public static String getYearByMillisecond(long millisecond) {
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        return yearFormat.format(new Date(millisecond));
    }

    /**
     * 根据输入日期返回月  08
     *
     * @param millisecond
     * @return
     */
    public static String getMonthByMillisecond(long millisecond) {
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        return monthFormat.format(new Date(millisecond));
    }

    /**
     * 根据输入当前时间、分割范围获取分组 后缀   ,比如2022年4月份输入 periodScope=2,  返回2022_02
     *
     * @param millisecond
     * @param periodScope
     * @return
     */
    public static String getPeriodScopeSuffixWithYear(long millisecond, int periodScope, String joinStr) {
        String month = getMonthByMillisecond(millisecond);
        String year = getYearByMillisecond(millisecond);
        Double num = Double.parseDouble(month) / periodScope;
        String suffix = String.format("0%.0f", num);
        if (suffix.length() > 2) {
            return year + joinStr + suffix.substring(suffix.length() - 2);
        }
        return year + joinStr + suffix;
    }

    public static Integer calcMaxScopeSuffixWithoutYear(int periodScope) {
        int year = Integer.parseInt(getYearByMillisecond(System.currentTimeMillis()));
        String month = getMonthByMillisecond(getYearLast(year).getTime());
        return Integer.parseInt(String.format("0%.0f", Double.parseDouble(month) / periodScope));
    }

    /**
     * 获取某年的第一天
     * @param year
     * @return
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();

    }

    /**
     * 获取某年最后一天
     * @param year
     * @return
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();

    }
}
