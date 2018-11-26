package com.wangtiansoft.KingDarts.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;

/**
 * Created by Tibers on 16/5/9.
 */
public class DateUtil {
    private static final String Pattern_DateFormatForDay       = "^\\d{4}-\\d{2}-\\d{2}";
    private static final String Pattern_DateFormatForDayPoint  = "^\\d{4}.\\d{2}.\\d{2}";
    private static final String DateFormat_YYYY_MM_DD          = "yyyy-MM-dd";
    private static final String DateFormat_YYYY_MM_DD_HH_MM    = "yyyy-MM-dd HH:mm:ss";
    private static final String DateFormat_YYYY_MM_DD_POINT    = "yyyy.MM.dd";
    private static final String DateFormat_YYYY_MM_DD_HH_MM_SS = "yyyyMMddHHmmss";
    private static final int mouth_type_begin              = 0;
    private static final int mouth_type_end              = 1;
    private static final int day_type_begin              = 0;
    private static final int day_type_end              = 1;

    public static boolean checkTimeFormatForDay(String beginTime) {
        Pattern pattern = Pattern.compile(Pattern_DateFormatForDay);
        Matcher matcher = pattern.matcher(beginTime);
        return matcher.matches();
    }

    /**
     * 日期格式化输出 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String mongoDateToSave(Date date) {
        return new SimpleDateFormat(DateFormat_YYYY_MM_DD).format(date);
    }

    /**
     * 日期格式化输出 yyyy-MM-dd
     *
     * @return
     */
    public static Date stringSingleToDate(String date) {
        try {
            return new SimpleDateFormat(DateFormat_YYYY_MM_DD).parse(date);
        } catch (ParseException e) {
            throw new AppRuntimeException("日期格式错误[" + DateFormat_YYYY_MM_DD + "]");
        }
    }

    /**
     * 日期格式化输出 yyyy-MM-dd
     *
     * @return
     */
    public static Date stringSingleToDate(String formatString, String date) {
        try {
            return new SimpleDateFormat(formatString).parse(date);
        } catch (ParseException e) {
            throw new AppRuntimeException("日期格式错误[" + DateFormat_YYYY_MM_DD + "]");
        }
    }

    /**
     * 日期格式化输出 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static Date stringCompleteToDate(String date) {
        try {
            return new SimpleDateFormat(DateFormat_YYYY_MM_DD_HH_MM).parse(date);
        } catch (ParseException e) {
            throw new AppRuntimeException("日期格式错误[" + DateFormat_YYYY_MM_DD_HH_MM + "]");
        }
    }

    /**
     * 日期格式化输出 yyyyMMddHHmmss
     *
     * @return
     */
    public static String dateToSave(Date date) {
        return new SimpleDateFormat(DateFormat_YYYY_MM_DD_HH_MM_SS).format(date);
    }


    public static int getTodaySurplusTime() {
        Long day = System.currentTimeMillis() / 86400000 + 1;
        return (int) (day * 86400 - System.currentTimeMillis() / 1000);
    }

    /**
     * @param currentInspectTime
     * @return
     */
    public static boolean checkTimeInToday(String currentInspectTime) {
        if (StringUtils.isEmpty(currentInspectTime)) {
            return false;
        } else {
            return mongoDateToSave(new Date()).equals(currentInspectTime.trim().substring(0, 10));
        }
    }
    
    /**
     * 是否时间是否是本月时间
     * @param currentInspectTime
     * @return
     */
    public static boolean checkTimeInMonth(String currentInspectTime) {
        if (StringUtils.isEmpty(currentInspectTime)) {
            return false;
        } else {
            return new SimpleDateFormat("yyyy-MM").format(new Date()).equals(currentInspectTime.trim().substring(0, 7));
        }
    }

    public static String getCurrentTimeWithMinute() {
        return new SimpleDateFormat(DateFormat_YYYY_MM_DD_HH_MM).format(new Date());
    }

    public static String getCurrentTimeByHome() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public static String getTimeWithMinuteByDate(Date time) {
        return new SimpleDateFormat(DateFormat_YYYY_MM_DD_HH_MM).format(time);
    }

    public static Long getTimeStamp(String timeString) {
        Pattern pattern = Pattern.compile(Pattern_DateFormatForDayPoint);
        Matcher matcher = pattern.matcher(timeString);
        if (!matcher.matches()) {
            throw new AppRuntimeException("日期格式错误[" + DateFormat_YYYY_MM_DD_POINT + "]");
        }
        try {
            return new SimpleDateFormat(DateFormat_YYYY_MM_DD_POINT).parse(timeString).getTime();
        } catch (ParseException e) {
            throw new AppRuntimeException("日期格式错误[" + DateFormat_YYYY_MM_DD_POINT + "]");
        }
    }

    /**
     * 获取当前月的第一天日期
     *
     * @return
     */
    public static String dateToMonth() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(cal_1.getTime());
        return firstDay;
    }
    
    /**
     * 获取当前月的最后一天日期
     *
     * @return
     */
    public static String dateToMonthLast() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, 1);
        cal_1.set(Calendar.DAY_OF_MONTH,0);//设置为0,当前日期既为本月最后一天
        String lastDay = format.format(cal_1.getTime());
        return lastDay;
    }


    /**
     * 获取某月的最后一天日期
     *
     * @return
     */
    public static Date dateToMonthLast(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);//设置为0,当前日期既为本月最后一天
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某月的第一天日期
     *
     * @return
     */
    public static Date dateToMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    
    /**
     * 获取某月的所有日期
     */
    public static List<String> allDateToMonth(Date date,String formatStr) {
    	List<String> dayList=new ArrayList<>();
    	SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);//设置为0,当前日期既为某月最后一天
        SimpleDateFormat formatCount = new SimpleDateFormat("dd");
        String dayLast=format.format(calendar.getTime());
        int dayCount = Integer.parseInt(formatCount.format(calendar.getTime()));
        for (int i = 1; i <dayCount; i++) {
        	 calendar.set(Calendar.DAY_OF_MONTH, i);//设置为0,当前日期既为某月最后一天
        	 dayList.add(format.format(calendar.getTime()));
		}
        dayList.add(dayLast);
        return dayList;
    }
    
    /**
     * 获取上一个月
     *
     * @return
     */
    public static Date getLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 根据日期获得所在周的星期一
     *
     * @param mdate
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date dateToWeek(Date mdate) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(mdate);
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        //所在周开始日期
        //        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
        //        cal.add(Calendar.DAY_OF_WEEK, 6);
        //所在周结束日期
        //        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
        return cal.getTime();
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static String formatDateBy(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String str = sdf.format(date);
        return str;
    }

    public static String formatDateYear(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        String str = sdf.format(date);
        return str;
    }


    /**
     * 将某个日期增加指定天数，并返回结果。如果传入负数，则为减。
     *
     * @param date    要操作的日期对象
     * @param ammount 要增加天数
     * @return 结果日期对象
     */
    public static Date addDate(final Date date, final int ammount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, ammount);
        return c.getTime();
    }

    /**
     * 将某个日期增加指定年，并返回结果。如果传入负数，则为减。
     *
     * @param date    要操作的日期对象
     * @param ammount 要增加天数
     * @return 结果日期对象
     */
    public static Date addYear(final Date date, final int ammount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, ammount);
        return c.getTime();
    }

    /**
     * 返回间隔描述 now大
     *
     * @param time
     * @return
     */
    public static int getNowIntervalSecond(Date time) {
        int interval = (int) ((System.currentTimeMillis() - time.getTime()) / 1000);
        return interval;
    }

    /**
     * 将日期格式字符串按照制定的格式，进行格式化，返回格式化后的日期
     *
     * @param dateStr
     * @return
     */
    public static Date formatDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            throw new AppRuntimeException("日期格式错误");
        }
        return date;
    }

    /**
     * 将日期格式字符串按照制定的格式，进行格式化，返回格式化后的日期
     *
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static Date formatDate(String dateStr, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            throw new AppRuntimeException("日期格式错误");
        }
        return date;
    }

    public static Date addDateMonth(final Date date, final int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) // 同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    timeDistance += 366;
                } else // 不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else { // 不同年
            return day2 - day1;
        }
    }

    /**
     * 当前第几季度
     *
     * @param date
     * @return
     */
    public static Integer getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    public static boolean checkDateInc(List<Date> dates) {
        boolean flag = true;
        if (dates != null && !dates.isEmpty() && dates.size() > 1) {
            for (int i = 1; i < dates.size(); i++) {
                if (dates.get(i).before(dates.get(i - 1))) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 返回指定时间  指定前后月  如果传入负数，则为减。
     * @param month
     * @param date
     * @return
     */
    public static Date getMonth(int month,Date date,int type){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        if(type == mouth_type_begin){
            c.set(Calendar.DAY_OF_MONTH, 1);
            return c.getTime();
        }else if(type == mouth_type_end){
            //获取某月最大天数
            int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最大天数
            c.set(Calendar.DAY_OF_MONTH, lastDay);
            return c.getTime();
        }
        return null;
    }

    /**
     * 得到指定时间 00点或23点59分
     * 0开始 1结束
     * @param date
     * @param type
     * @return
     */
    public static String getDay(Date date,int type){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(type==day_type_end){
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
            return getTimeWithMinuteByDate(cal.getTime());
        }else if(type==day_type_begin){
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);
            //时分秒（毫秒数）
            long millisecond = hour*60*60*1000 + minute*60*1000 + second*1000;
            //凌晨00:00:00
            cal.setTimeInMillis(cal.getTimeInMillis()-millisecond);
            return getTimeWithMinuteByDate(cal.getTime());
        }
        return null;
    }
    
    /**
     * 得到所在星期的星期一到星期日的日期
     * @return Date[]
     */
    public static List<String> getWeekDay() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
        	dates.add(mongoDateToSave(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
    
    /**
     * 得到今天是这个星期的第几天  从星期天开始算一周开始
     * @return Date[]
     */
    public static int getTodayWeekDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    
    /**
     * 今天剩余时间 精确到秒
     * @return String  HH:mm:ss 格式时间
     */
    public static Long getTodayTimeLeft() {
    	Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);    
        Long nowL= new Date().getTime();
        Long time=(cal.getTime().getTime()-nowL)/1000;
        /*Long hour=time/3600;
        Long minute=(time-hour*3600)/60;
        long second=time-hour*3600-minute*60;
        String hourStr=String.format("%0" + 2 + "d", hour);
        String minuteStr=String.format("%0" + 2 + "d", minute);
        String secondStr=String.format("%0" + 2 + "d", second);
        return hourStr+":"+minuteStr+":"+secondStr;*/
        return time;
    }
    public static void main(String[] args) {
    	System.out.println(DateUtil.allDateToMonth(DateUtil.stringSingleToDate("2018-07-01"),"yyyy-MM-dd"));
	}
}
