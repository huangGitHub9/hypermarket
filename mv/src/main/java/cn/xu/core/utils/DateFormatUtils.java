package cn.xu.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtils {


    public static Date getFormatDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(date);
        Date d2 = null;
        try {
            d2 = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d2;
    }

    public static Date getFormatDate(String parseFormat){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(parseFormat);
        String dateString = format.format(date);
        Date d2 = null;
        try {
            d2 = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d2;
    }

    public static String getDateStr(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(date);

        return dateString;
    }

    /**
     *
     * @param startTime 日期开始的时间
     * @param month 你要增加的月份
     * @return Date: 最终结束的日期
     */
    public static Date getEndMonthTime(Date startTime,int month,String parseFormat){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parseFormat);
        Calendar calendar = simpleDateFormat.getCalendar();
        calendar.setTime(startTime);

        calendar.add(Calendar.MONTH,month);
        //最后将calendar变为Data对象
        Date end = calendar.getTime();
        return end;
    }
}
