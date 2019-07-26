package com.brook.utils;

import com.brook.bean.DateType;
import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

    /**
     * 获取 当天时间
     *
     * @return 当天时间
     */
    public static LocalDateTime getToday() {
        return LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
    }

    /**
     * 获取当前时间 yyyyMMddHHmmss
     *
     * @return String
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);

        return s;
    }


    public static String getFormatDateStr(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return df.format(date);
    }

    public final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";// Timestamp format must be yyyy-mm-dd
    // hh:mm:ss

    public static Date str2Date(String str, String format) {
        if (null == str || "".equals(str)) {
            return null;
        }
        // 如果没有指定字符串转换的格式，则用默认格式进行转换
        if (null == format || "".equals(format)) {
            format = DEFAULT_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(str);
            return date;
        } catch (ParseException e) {
            logger.error("DateUtils str2Date error.{}",e);
        }
        return null;
    }

    public static Date getFormatDate(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

        Date newdate = null;
        try {
            newdate = df.parse(date);
        } catch (ParseException e) {
            logger.error("DateUtils getFormatDate error.{}",e);
        }
        return newdate;
    }

    public static Date getFormatDateMD(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d", Locale.CHINA);

        Date newdate = null;
        try {
            newdate = df.parse(date);
        } catch (ParseException e) {
            logger.error("DateUtils getFormatDateMD error.{}",e);
        }
        return newdate;
    }

    public static Date getFormatDateNoSecond(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);

        Date newdate = null;
        try {
            newdate = df.parse(date);
        } catch (ParseException e) {
            logger.error("DateUtils getFormatDateNoSecond error.{}",e);
        }
        return newdate;
    }

    public static Date getFormatDateNoDay(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

        Date newdate = null;
        try {
            newdate = df.parse(date);
        } catch (ParseException e) {
            logger.error("DateUtils getFormatDateNoDay error.{}",e);
        }
        return newdate;
    }

    public static String getSimpleFormatStr(Long datetime){
        if (datetime == null){
            return null;
        }
        Date date = new Date(datetime);
        return DateUtils.getSimpleFormatStr(date);
    }

    public static String getSimpleFormatStr(Date date) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        try {
            return sf1.format(date);
        } catch (Exception e) {
            logger.error("DateUtils getSimpleFormatStr error.{}",e);
            return null;
        }
    }


    public static String getSimpleFormatStr(Date date,SimpleDateFormat shortDateFormat) {
        try {
            return shortDateFormat.format(date);
        } catch (Exception e) {
            logger.error("DateUtils getSimpleFormatStr error.{}",e);
            return null;
        }
    }


    public static String getSimpleFormatStr(String date){
        try {
            date = date.replace(" ","");
            date = date.replace("T"," ");
            date = date.replace("+08:00","");
            return date;
        }catch (Exception e){
            logger.error("DateUtils getSimpleFormatStr error.{}",e);
        }
        return null;
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

    public static String getSimpleFormatStrNoyear(Date date) {
        SimpleDateFormat sf1 = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
        try {
            return sf1.format(date);
        } catch (Exception e) {
            logger.error("DateUtils getSimpleFormatStrNoyear error.{}",e);
            return null;
        }
    }

    public static Date getSimpleFormatDate(String date) {
        SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
        Date newdate = null;
        try {
            newdate = df.parse(date);
        } catch (ParseException e) {
            logger.error("DateUtils getSimpleFormatDate error.{}",e);
        }
        return newdate;
    }

    public static String getLastMonthDate(Date date, int page) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, page * (-1));
        Date oneMonthDate = calendar.getTime();
        // SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        // return df.format(oneMonthDate);
        return isoFormat(oneMonthDate);
    }

    public static String getLastDayDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day * (-1));
        Date oneMonthDate = calendar.getTime();
        // SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        // return df.format(oneMonthDate);
        return isoFormat(oneMonthDate);
    }

    public static String getLastDayDateMD(Date date, int page) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, page * (-1));
        Date oneMonthDate = calendar.getTime();
        String tempdate = getYearMonthDay(oneMonthDate);
        return tempdate;
    }

    public static String getLastDayDateM_D(Date date, int page) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, page * (-1));
        Date oneMonthDate = calendar.getTime();
        String tempdate = getSimpleFormatStrNoDay(oneMonthDate);
        return tempdate;
    }

    public static String getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String weekDay = "";
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                weekDay = "星期二";
                break;
            case 2:
                weekDay = "星期三";
                break;
            case 3:
                weekDay = "星期四";
                break;
            case 4:
                weekDay = "星期五";
                break;
            case 5:
                weekDay = "星期六";
                break;
            case 6:
                weekDay = "星期日";
                break;
            case 0:
                weekDay = "星期一";
                break;
            default:
                break;
        }
        return weekDay;
    }

    public static Date getCurrentMonthDate(Date date, int last) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, last * (-1));
        Date lastDate = calendar.getTime();
        return lastDate;
    }

    /**
     * 转换Date位xxxx年x月x日
     *
     * @param date
     * @return
     */
    public static String getYearMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月"
                + calendar.get(Calendar.DAY_OF_MONTH) + "日";
    }

    public static String getYearMonthDay2(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
                + calendar.get(Calendar.DAY_OF_MONTH) + "";
    }

    // 获取月日
    public static String getMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日";
    }

    /**
     * ************************************************************************ The number of milliseconds in a minute.
     */
    private final static long MS_IN_MINUTE = 60 * 1000;

    /**
     * ************************************************************************ The number of milliseconds in an hour.
     */
    private final static long MS_IN_HOUR = 60 * 60 * 1000;

    /***************************************************************************
     * The number of milliseconds in a day.
     */
    // private final static long MS_IN_DAY = 24 * 60 * 60 * 1000;
    /**
     * 本地日期格式符号表
     */
    public final static DateFormatSymbols localDateFormatSymbols = new DateFormatSymbols(Locale.getDefault());

    /**
     * 月年格式
     */
    public static final SimpleDateFormat monthYearFormat = new SimpleDateFormat("MMM yyyy", Locale.getDefault());

    /**
     * 年月格式
     */
    public static final SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM", Locale.CHINA);

    /**
     * 日格式
     */
    public static final SimpleDateFormat dayFormat = new SimpleDateFormat("d", Locale.CHINA);

    /**
     * 时间格式
     */
    public static final SimpleDateFormat minuteFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);

    /**
     * 时间格式
     */
    public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);

    /**
     * 24小时的时间 格式
     */
    public static final SimpleDateFormat time24Format = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);

    /**
     * 星期格式
     */
    public static final SimpleDateFormat weekFormat = new SimpleDateFormat("EEE", Locale.CHINA);

    /**
     * 年月日 短格式
     */
    public static final SimpleDateFormat shortDateFormat = new SimpleDateFormat("yy-MM-dd", Locale.CHINA);

    /**
     * 年月日 长格式
     */
    public static final SimpleDateFormat longDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

    /**
     * 年月日 天 长格式
     */
    public static final SimpleDateFormat longHoutDateFormat = new SimpleDateFormat("yyyy-MM-dd HH", Locale.CHINA);

    /**
     * 年月日 时分秒 短格式
     */
    public final static SimpleDateFormat shortDatetimeFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);

    /**
     * 年月日 时分秒 长格式
     */
    public final static SimpleDateFormat longDatetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    private final static SimpleDateFormat isoFormat_ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CHINA);

    /**
     * ************************************************************************ Format the given date and return the
     * resulting string in ISO 8601 format. The format is as follows: "yyyy-MM-dd'T'HH:mm:ss.SSS[Z|[+|-]HH:mm]".
     *
     * @param inputDate The date to be converted into string format.
     * @return The formatted date/time string.
     */
    public static String isoFormat(Date inputDate) {

        // Setup the date format and convert the given date.
        // SimpleDateFormat dateFormat = new
        // SimpleDateFormat(ISO_FORMAT);
        String dateString = isoFormat_.format(inputDate);

        // Determine the time zone and concatenate the time zone
        // designator
        // onto the formatted date/time string.
        TimeZone tz = isoFormat_.getTimeZone();
        String tzName = tz.getDisplayName();
        if (tzName.equals("Greenwich Mean Time")) {
            dateString = dateString.concat("Z");
        } else {
            // Determine the hour offset. Add an hour if daylight
            // savings
            // is in effect.
            long tzOffsetMS = tz.getRawOffset();
            long tzOffsetHH = tzOffsetMS / MS_IN_HOUR;
            if (tz.inDaylightTime(inputDate)) {
                tzOffsetHH = tzOffsetHH + 1;
            }
            String hourString = String.valueOf(Math.abs(tzOffsetHH));
            if (hourString.length() == 1) {
                hourString = "0" + hourString;
            }

            // Determine the minute offset.
            long tzOffsetMMMS = tzOffsetMS % MS_IN_HOUR;
            long tzOffsetMM = 0;
            if (tzOffsetMMMS != 0) {
                tzOffsetMM = tzOffsetMMMS / MS_IN_MINUTE;
            }
            String minuteString = String.valueOf(tzOffsetMM);
            if (minuteString.length() == 1) {
                minuteString = "0" + minuteString;
            }

            // Determine the sign of the offset.
            String sign = "+";
            if (String.valueOf(tzOffsetMS).indexOf("+") != -1) {
                sign = "-";
            }

            dateString = dateString.concat(sign + hourString + ":" + minuteString);
        }

        return (dateString);
    }

    /**
     * ************************************************************************ Parse the given date/time string in ISO
     * 8601 format and return the resulting <code>Date</code> object. The format is as follows:
     * "yyyy-MM-dd'T'HH:mm:ss.SSS[Z|[+|-]HH:mm]".
     *
     * @param inputString The string to be parsed.
     * @return The resulting Date object.
     * @throws ParseException If the string does not match the date/time format.
     */
    public static Date isoParse(String inputString) throws ParseException {
        // Setup the date format.
        // SimpleDateFormat dateFormat = new
        // SimpleDateFormat(ISO_FORMAT);
        isoFormat_.setLenient(false);

        // The length of the input string should be at least 24
        // characters.
        if (inputString.length() < 24) {
            throw new ParseException(
                    "An exception occurred because the input date/time string was not at least 24 characters in length.",
                    inputString.length());
        }

        // Evaluate the the specified offset and set the time zone.
        String offsetString = inputString.substring(23);
        if (offsetString.equals("Z")) {
            isoFormat_.setTimeZone(TimeZone.getTimeZone("Greenwich Mean Time"));
        } else if (offsetString.startsWith("-") || offsetString.startsWith("+")) {
            SimpleDateFormat offsetFormat = new SimpleDateFormat();
            if (offsetString.length() == 3) {
                offsetFormat.applyPattern("HH");
            } else if (offsetString.length() == 6) {
                offsetFormat.applyPattern("HH:mm");
            } else {
                throw new ParseException(
                        "An exception occurred because the offset portion was not the valid length of 3 or 6 characters.",
                        25);
            }

            // Validate the given offset.
            offsetFormat.setLenient(false);
            // Date offsetDate = offsetFormat.parse(offsetString.substring(1));

            // Set the time zone with the validated offset.
            isoFormat_.setTimeZone(TimeZone.getTimeZone("GMT" + offsetString));
        } else {
            throw new ParseException(
                    "An exception occurred because the offset portion of the input date/time string was not 'Z' or did not start with '+' or '-'.",
                    24);
        }

        // Parse the given string.
        Date parseDate = isoFormat_.parse(inputString);

        return (parseDate);
    }

    public static String getWeekOfDate(Date date) {
        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    /**
     * 输入一个日期 找到当周的周一
     *
     * @param date
     * @return
     */
    public static String FindMonday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int temp = calendar.get(Calendar.DAY_OF_WEEK);
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            temp = 6;
        } else {
            temp -= 2;
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE) - temp);
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
                + calendar.get(Calendar.DATE);
    }

    /**
     * 输入一个日期 找到当周的周一
     *
     * @param date
     * @return
     */
    public static Date FindMondayReturnDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int temp = calendar.get(Calendar.DAY_OF_WEEK);
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            temp = 6;
        } else {
            temp -= 2;
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE) - temp);
        return calendar.getTime();
    }

    /**
     * 输入一个日期 找到当周的周日
     *
     * @param date
     * @return
     */
    public static String FindSunday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int temp = calendar.get(Calendar.DAY_OF_WEEK);
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            temp = 6;
        } else {
            temp -= 2;
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
                + (6 - temp));
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
                + calendar.get(Calendar.DATE);
    }

    /**
     * 输入一个日期 找到当周的周日
     *
     * @param date
     * @return
     */
    public static Date FindSundayReturnDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int temp = calendar.get(Calendar.DAY_OF_WEEK);
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            temp = 6;
        } else {
            temp -= 2;
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
                + (6 - temp));
        return calendar.getTime();
    }

    /**
     * 获取某年某月天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 日期相减算日期
     *
     * @param beginDateStr 起始日期
     * @param endDateStr   结束日期
     * @return
     */
    public static int getDaySub(String beginDateStr, String endDateStr) {
        int day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate;
        Date endDate;
        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
            day = (int) ((endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            logger.error("DateUtils getDaySub error.{}",e);
        }
        return day;
    }

    /**
     * 输入一个Date 返回这一天的这个月第一天的周一
     *
     * @param date
     * @return
     */
    public static List<Date> getDateList(Date date) {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar Month = Calendar.getInstance();
        Month.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        Month.setTime(FindMondayReturnDate(Month.getTime()));
        for (int i = 0; i < 42; i++) {
            dates.add(Month.getTime());
            Month.add(Calendar.DATE, 1);
        }
        return dates;
    }

    /**
     * 获取两个时间的时间查 如1天2小时30分钟
     */
    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
//        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
//        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return hour + "";
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
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                formatter.
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

    public static void main(String[] args) {


//        Date startDate = DateUtils.dateAddDays(date, -10);
//        List<String> aa = DateUtils.getBetweenDates(Calendar.DAY_OF_YEAR, startDate, date, DateUtils.longDateFormat);
//        System.out.println(aa);


//        LocalDateTime end = LocalDateTime.now();
//        LocalDateTime start = end.plusHours(-24);
//        List<String> bb = DateUtils.getBetweenDates(Calendar.HOUR_OF_DAY, start, end);
//        for (String b:bb){
//            System.out.println(b);
//        }

//        Date startDateMinute = DateUtils.dateAddMinute(date, -10);
//        List<String> cc = DateUtils.getBetweenDates(Calendar.MINUTE, startDateMinute, date, DateUtils.longDatetimeFormat);
//        System.out.println(cc);
        String ss = LocalDateTime.now().plusHours(-20).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH"));
        System.out.println(ss);
    }

}
