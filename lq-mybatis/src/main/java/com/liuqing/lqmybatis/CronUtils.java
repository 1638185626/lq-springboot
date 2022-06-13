package com.liuqing.lqmybatis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author liuqing01
 * @version 1.0
 * @description TODO
 * @date 2021/11/9 10:14
 */
public class CronUtils {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ? yyyy");

    /***
     *  功能描述：日期转换cron表达式
     * @param date
     * @return
     */
    public static String formatDateByPattern(Date date) {
        String formatTimeStr = null;
        if (Objects.nonNull(date)) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /***
     * convert Date to cron, eg "0 07 10 15 1 ? 2016"
     * @param date  : 时间点
     * @return
     */
    public static String getCron(Date date) {
        return formatDateByPattern(date);
    }

    public static void main(String[] args) {
        String byPattern = formatDateByPattern(new Date());
        System.out.println(byPattern);
    }
}
