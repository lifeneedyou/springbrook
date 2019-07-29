package com.brook.utils;

import com.brook.bean.DateType;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;


/**
 * Date Time Utils
 * Created with IntelliJ IDEA.
 * User: songlijun
 * Date: 2017/9/6
 * Time: 上午10:38
 * To change this template use File | Settings | File Templates.
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 生成时间列表
     *
     * @param dateType
     * @param intervalValue
     * @return
     */
    public static List<String> initDateList(DateType dateType, Integer intervalValue) {
        // 初始化天时间列表
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime;
        List<String> dateList = Lists.newArrayList();
        switch (dateType) {
            case DAY:
                startTime = endTime.plusDays(-intervalValue);
                dateList = DateUtils.getBetweenDates(Calendar.DAY_OF_YEAR, startTime, endTime);
                break;
            case HOUR:
                startTime = endTime.plusHours(-intervalValue);
                dateList = DateUtils.getBetweenDates(Calendar.HOUR_OF_DAY, startTime, endTime);
                break;
            case MINUTE:
                startTime = endTime.plusMinutes(- intervalValue);
                dateList = DateUtils.getBetweenDates(Calendar.MINUTE,startTime,endTime);
                break;
            default:
                logger.error("buildDateList dateType:{} 类型错误", dateType);
                return null;
        }
//        Collections.sort(dateList, (str1, str2) -> str1.compareTo(str2));
        return dateList;
    }



    public static String getFormatDateStr(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return df.format(date);
    }

    public final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";// Timestamp format must be yyyy-mm-dd
    // hh:mm:ss



    public static String getSimpleFormatStr(Date date) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        try {
            return sf1.format(date);
        } catch (Exception e) {
            logger.error("DateUtils getSimpleFormatStr error.{}",e);
            return null;
        }
    }




    public static String getSimpleFormatStrNoDay(Date date) {
        return getSimpleFormatStrNoDay(date, null);
    }

    public static String getSimpleFormatStrNoDay(Date date, String rep) {
        SimpleDateFormat sf1;
        if (rep != null) {
            sf1 = new SimpleDateFormat("yyyy-MM-dd".replace("-", rep), Locale.CHINA);
        } else {
            sf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        }

        try {
            return sf1.format(date);
        } catch (Exception e) {
            logger.error("DateUtils getSimpleFormatStrNoDay error.{}",e);
            return "";
        }
    }



    public static Date dateAddMinute(Date startDate, int minutes){
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + minutes);
        return c.getTime();
    }

    /**
     * 时间加减小时
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param hours     加减的小时
     * @return Date
     */
    public static Date dateAddHours(Date startDate, int hours) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.HOUR, c.get(Calendar.HOUR) + hours);
        return c.getTime();
    }

    /**
     * 时间加减天数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param days      加减的天数
     * @return Date
     */
    public static Date dateAddDays(Date startDate, int days) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.DATE, c.get(Calendar.DATE) + days);
        return c.getTime();
    }

    /**
     * 时间加减月数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param months    加减的月数
     * @return Date
     */
    public static Date dateAddMonths(Date startDate, int months) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + months);
        return c.getTime();
    }
    /**
     * 时间加减年数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param years     加减的年数
     * @return Date
     */
    public static Date dateAddYears(Date startDate, int years) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) + years);
        return c.getTime();
    }



    public static List<String> getBetweenDates(int dateType,LocalDateTime start, LocalDateTime end){
        List<String> list = new ArrayList<>();
        long distance = 0;
        String formatStr = "";
        switch (dateType){
            case Calendar.DAY_OF_YEAR:
                distance = ChronoUnit.DAYS.between(start, end);
                formatStr = "yyyy-MM-dd";
                break;
            case Calendar.HOUR_OF_DAY:
                distance = ChronoUnit.HOURS.between(start,end);
                formatStr = "yyyy-MM-dd HH:00:00";
                break;
            case Calendar.MINUTE:
                distance = ChronoUnit.MINUTES.between(start,end);
                formatStr = "yyyy-MM-dd HH:mm:00";
                break;
        }



        if (distance < 1) {
            return list;
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatStr);

        Stream.iterate(start, d -> {
            switch (dateType){
                case Calendar.DAY_OF_YEAR:
                    return d.plusDays(1);
                case Calendar.HOUR_OF_DAY:
                    return d.plusHours(1);
                case Calendar.MINUTE:
                    return d.plusMinutes(1);
            }
            return null;
        }).limit(distance + 1).forEach(f -> {
            list.add(formatter.format(f));
        });
        return list;
    }


}
