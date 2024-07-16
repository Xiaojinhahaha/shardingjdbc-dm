package com.example.sharding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class DateUtils {
    /**
     * 定义常量*
     */
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_DATE_STR = "yyyy-MM-dd";


    /**
     * 字符串转日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, DATE_FULL_STR);
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param format  日期格式
     * @return
     */
    public static Date parse(String strDate, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Date parseDate(String strDate) {
        return parse(strDate, DATE_DATE_STR);
    }

}
