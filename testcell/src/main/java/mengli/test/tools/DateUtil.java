package mengli.test.tools;

import mengli.test.externaltools.DiagnoseLogger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mengli.huang on 2015/11/19.
 */
public class DateUtil {
    private static final ConcurrentHashMap<String, Date> coverDateCache = new ConcurrentHashMap<String, Date>(400, 0.75f, 64);
    protected static final DiagnoseLogger logger = DiagnoseLogger.getInstance();
    public static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static Date getCurDate() {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    /**
     * yyyy/MM/dd -> yyyy-MM-dd
     */
    public static String transformDateInt(String dateIntStr) {
        if (StringUtil.isEmpty(dateIntStr)) return "";
        if (dateIntStr.length() != 10) return dateIntStr;
        return dateIntStr.replace("/", "-");
    }

    /**
     * days between day1 and day2
     *
     * @param date1 format： yyyy-MM-dd
     * @param date2 format： yyyy-MM-dd
     * @return
     */
    public static int daysBetween(String date1, String date2) {
        return daysBetween(date1, date2, true);
    }

    /**
     * days between date1 and date2
     *
     * @param date1 format： yyyy-MM-dd
     * @param date2 format： yyyy-MM-dd
     * @param abs
     * @return
     */
    public static int daysBetween(String date1, String date2, boolean abs) {
        if (StringUtil.isEmpty(date1) || StringUtil.isEmpty(date2)) {
            return 0;
        }
        try {
            Date d1 = convertDate1(date1);
            Date d2 = convertDate1(date2);
            long t1 = d1.getTime();
            long t2 = d2.getTime();
            long interval = t2 - t1;
            if (abs) interval = Math.abs(interval);
            return (int) (interval / (1000 * 60 * 60 * 24));
        } catch (Exception e) {
            return 0;
        }
    }

    public static Date convertDate1(String _dateValue) {
        if (_dateValue == null)
            return null;

        Date date = coverDateCache.get(_dateValue);
        if (date != null) {
            return date;
        }

        if (_dateValue.indexOf("null") >= 0)
            return null;

        try {
            date = parseDate(_dateValue).toDate();
            coverDateCache.putIfAbsent(_dateValue, date);
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    public static DateTime parseDate(String dateStr, DateTimeFormatter formatter) {
        return parseDate(dateStr, formatter, true);
    }

    public static DateTime parseDate(String dateStr, DateTimeFormatter formatter, boolean isLog) {
        DateTime dateTime = null;
        try {
            dateTime = formatter.parseDateTime(dateStr);
        } catch (Exception e) {
            if (isLog) logger.error("Error in parse date: " + dateStr + "! ", e);
        }
        return dateTime;
    }

    /**
     * format like "2014-06-24"
     *
     * @param dateStr
     * @return
     */
    public static DateTime parseDate(String dateStr) {
        return parseDate(dateStr, dateFormatter);
    }

    public static String getNextMonth(String curDate) {
        String date=GetSysDate(dateFormatter.toString(), curDate,0,1,0);
        return date;
    }
    public static String getNextDay(String curDate) {
        String date=GetSysDate(dateFormatter.toString(), curDate,0,0,1);
        return date;
    }

    public static String GetSysDate(String format, String StrDate, int year, int month,int day) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sFmt = new SimpleDateFormat(format);
        cal.setTime(sFmt.parse((StrDate), new ParsePosition(0)));

        if (day != 0) {
            cal.add(cal.DATE, day);
        }
        if (month != 0) {
            cal.add(cal.MONTH, month);
        }
        if (year != 0) {
            cal.add(cal.YEAR, year);

        }
        return sFmt.format(cal.getTime());
    }
    public static boolean compareDate(String date1,String date2){
        return true;//>=
    }
    public static boolean compareDateSmall(String date1,String date2){
        return true;//<=
    }
}
