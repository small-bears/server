package self.cases.teams.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     *  默认日期格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     *  默认时间格式 HH:mm:ss
     */
    public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";

    /**
     * 年、月、日  yyyy-MM-dd
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     * 日期格式化功能类
     * @param format
     * @return
     */
    public static SimpleDateFormat getDateFormat(String format){

        return new SimpleDateFormat(format);
    }

    /**
     * 日期格式化处理
     * @param date 要格式化的日期
     * @param format 格式化字符串
     * @return 格式化后的日期
     */
    public static String formatDateTime(Date date, String format ){

        return getDateFormat(format).format(date);
    }

    /**
     * 转换字符串为日期类型
     * @param date 要转换的日期字符串
     * @param format 转换的格式
     * @return 转换后的日期
     * @throws ParseException
     */
    public static Date parseDate(String date, String format) throws ParseException {

        return getDateFormat(format).parse(date);
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static long getCurrent() {

        return System.currentTimeMillis();
    }

    /**
     * 获取指定格式的系统时间(当前时间)
     * @param format 指定的时间格式
     * @return 当前系统时间
     */
    public static String getNowDate(String format){

        return formatDateTime(new Date(), format);
    }

    /**
     * 获取默认格式的当前系统时间(当前时间)
     * 时间格式： yyyy-MM-dd HH:mm:ss
     * @return 当前系统时间
     */
    public static String getNowDate(){

        return formatDateTime(new Date(), DATETIME_DEFAULT_FORMAT);
    }

    /**
     * 获取当前时间
     * 时间格式：HH:mm:ss
     * @return
     */
    public static String getNowTime(){

        return formatDateTime(new Date(), TIME_DEFAULT_FORMAT);
    }
}