package com.hellozjf.test.springboot.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jingfeng Zhou
 */
public class MyDateUtils {

    /**
     * 一小时的毫秒数
     */
    public static final Long HOUR_LONG_MS = 1000L * 60 * 60;

    /**
     * 一天的毫秒数
     */
    public static final Long DAY_LONG_MS = HOUR_LONG_MS * 24;

    /**
     * 获取year-month-day-hour的毫秒数
     * @param year
     * @param month
     * @param day
     * @param hour
     * @return
     */
    public static Long getMsTime(int year, int month, int day, int hour) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, 0);
        return getMsTime(localDateTime);
    }

    /**
     * 获取year-month-day的毫秒数
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Long getMsTime(int year, int month, int day) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, 0, 0);
        return getMsTime(localDateTime);
    }

    /**
     * 获取year-month的毫秒数
     * @param year
     * @param month
     * @return
     */
    public static Long getMsTime(int year, int month) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, 1, 0, 0);
        return getMsTime(localDateTime);
    }

    /**
     * 返回year-month最大的天数
     * @param year
     * @param month
     * @return
     */
    public static Integer getMaxDayOfMonth(int year, int month) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, 1, 0, 0);
        localDateTime = localDateTime.plusMonths(1).minusDays(1);
        return localDateTime.getDayOfMonth();
    }

    /**
     * 返回year-month，这个月的毫秒数
     * @param year
     * @param month
     * @return
     */
    public static Long getMonthLongMs(int year, int month) {
        Integer maxDayOfMonth = getMaxDayOfMonth(year, month);
        return maxDayOfMonth * DAY_LONG_MS;
    }

    /**
     * 获取今天0点的毫秒数
     * @return
     */
    public static Long getTodayStartMsTime() {
        return getTodayHourStartMsTime(0);
    }

    /**
     * 获取今天hour点的毫秒数
     * @return
     */
    public static Long getTodayHourStartMsTime(int hour) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.withNano(0).withSecond(0).withMinute(0).withHour(hour);
        return getMsTime(localDateTime);
    }

    /**
     * 获取本月第一天的毫秒数
     * @return
     */
    public static Long getMonthStartMsTime() {
        return getMonthDayStartMsTime(1);
    }

    /**
     * 获取本月第day天的毫秒数
     * @return
     */
    public static Long getMonthDayStartMsTime(int day) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.withNano(0).withSecond(0).withMinute(0).withHour(0).withDayOfMonth(day);
        return getMsTime(localDateTime);
    }

    /**
     * 获取本年第一月的毫秒数
     * @return
     */
    public static Long getYearStartMsTime() {
        return getYearMonthStartMsTime(1);
    }

    /**
     * 获取本年第month月的毫秒数
     * @return
     */
    public static Long getYearMonthStartMsTime(int month) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.withNano(0).withSecond(0).withMinute(0).withHour(0).withDayOfMonth(1).withMonth(month);
        return getMsTime(localDateTime);
    }

    /**
     * 获取当前的小时数
     * @return
     */
    public static Integer getCurrentHour() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.getHour();
    }

    /**
     * 获取当前的日
     * @return
     */
    public static Integer getCurrentDay() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.getDayOfMonth();
    }

    /**
     * 获取当前的月
     * @return
     */
    public static Integer getCurrentMonth() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.getMonthValue();
    }

    /**
     * 获取当前的年
     * @return
     */
    public static Integer getCurrentYear() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.getYear();
    }

    /**
     * 将本地时间转化为时间戳
     * @param localDateTime
     * @return
     */
    private static Long getMsTime(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }



    /**
     * 获取year-month-day需要统计的时间戳列表
     * 只传year，返回各月的时间戳
     * 只传year和month，返回各日的时间戳
     * 都传，返回各小时的时间戳
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static List<Long> getTimeList(Integer year, Integer month, Integer day) {

        List<Long> timeList = new ArrayList<>();

        // 获取当前时间
        Integer currentYear = MyDateUtils.getCurrentYear();
        Integer currentMonth = MyDateUtils.getCurrentMonth();
        Integer currentDay = MyDateUtils.getCurrentDay();
        Integer currentHour = MyDateUtils.getCurrentHour();

        // 判断要解析的是什么时间
        if (year != null) {
            if (month != null) {
                if (day != null) {
                    // 要解析的是一天内的各小时段数据
                    if (year.equals(currentYear) && month.equals(currentMonth) && day.equals(currentDay)) {
                        // 说明是今天，那么不需要统计到最后
                        for (int i = 0; i <= currentHour; i++) {
                            timeList.add(MyDateUtils.getMsTime(year, month, day, i));
                        }
                        // 再增加当前小时下一个小时的时间戳
                        timeList.add(MyDateUtils.getMsTime(year, month, day, currentHour) + MyDateUtils.HOUR_LONG_MS);
                    } else {
                        // 说明不是今天，需要统计到最后
                        for (int i = 0; i <= 23; i++) {
                            timeList.add(MyDateUtils.getMsTime(year, month, day, i));
                        }
                        // 再增加下一天开始的时间戳
                        timeList.add(MyDateUtils.getMsTime(year, month, day, 23) + MyDateUtils.HOUR_LONG_MS);
                    }
                } else {
                    // 要解析的是一月内的各天段数据
                    if (year.equals(currentYear) && month.equals(currentMonth)) {
                        // 说明是本月，那么不需要统计到最后
                        for (int i = 1; i <= currentDay; i++) {
                            timeList.add(MyDateUtils.getMsTime(year, month, i));
                        }
                        // 再增加当前天下一天的时间戳
                        timeList.add(MyDateUtils.getMsTime(year, month, currentDay) + MyDateUtils.DAY_LONG_MS);
                    } else {
                        // 说明不是本月，那么需要统计到最后
                        int maxDayOfMonth = MyDateUtils.getMaxDayOfMonth(year, month);
                        for (int i = 1; i <= maxDayOfMonth; i++) {
                            timeList.add(MyDateUtils.getMsTime(year, month, i));
                        }
                        // 再增加下个月开始的时间戳
                        timeList.add(MyDateUtils.getMsTime(year, month, maxDayOfMonth) + MyDateUtils.DAY_LONG_MS);
                    }
                }
            } else {
                // 要解析的是一年内的各月段数据
                if (year.equals(currentYear)) {
                    // 说明是本年，那么不需要统计到最后
                    for (int i = 1; i <= currentMonth; i++) {
                        timeList.add(MyDateUtils.getMsTime(year, i));
                    }
                    // 在增加当前月下一月的时间戳
                    timeList.add(MyDateUtils.getMsTime(year, currentMonth) + MyDateUtils.getMonthLongMs(year, currentMonth));
                } else {
                    // 说明不是本年，那么需要统计到最后
                    for (int i = 1; i <= 12; i++) {
                        timeList.add(MyDateUtils.getMsTime(year, i));
                    }
                    // 再增加下一年开始的时间戳
                    timeList.add(MyDateUtils.getMsTime(year, 12) + MyDateUtils.getMonthLongMs(year, 12));
                }
            }
        }

        return timeList;
    }
}
