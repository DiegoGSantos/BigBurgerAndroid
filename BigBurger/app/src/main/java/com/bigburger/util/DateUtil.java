package com.bigburger.util;

import android.content.Context;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static com.bigburger.util.UtilKt.isObjectNotNull;
import static com.bigburger.util.UtilKt.isStringValidNumber;

/**
 * Created by diegosantos on 2/29/16.
 */
public class DateUtil {

    public static final Locale brLocale = new Locale("pt", "BR");

    public static final String[] DATE_FORMATS = new String[] {
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd", "yyyy-MM", "dd/MM/yyyy"};

    public static final Date timestampToDate(String stamp){
        final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(Long.parseLong(stamp));
        return cal.getTime();
    }

    public static final long getDateTimeStamp(Date date){
        if (isObjectNotNull(date)){
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.setTime(date);
            calendar.set(Calendar.MILLISECOND, 0);

            return calendar.getTimeInMillis();
        }else{
            return 0;
        }
    }

    public static final long getCurrentTimeStamp(){

        Date date = new Date();

        if (isObjectNotNull(date)){
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.setTime(date);
            calendar.set(Calendar.MILLISECOND, 0);

            return calendar.getTimeInMillis();
        }else{
            return 0;
        }
    }

    public static final long getDateTimeStampLocal(Date date){
        if (isObjectNotNull(date)){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.MILLISECOND, 0);

            return calendar.getTimeInMillis();
        }else{
            return 0;
        }
    }

    public static final String getDayOfMonth(Date d) {

        SimpleDateFormat format = new SimpleDateFormat("dd", brLocale);

        return format.format(d);
    }

    public static final String getDayMonthYear(Date d) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", brLocale);

        return format.format(d);
    }

    public static final String getYearMonthDay(Date d) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM/dd", brLocale);

        return format.format(d);
    }

    public static final String getStringDateWithTime(Date d) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", brLocale);

        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        return format.format(d);
    }

    public static final String getStringDate(Date d) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", brLocale);

        return format.format(d);
    }

    public static final String getMonthYearNumber(Date d) {

        SimpleDateFormat format = new SimpleDateFormat("MM/yyyy", brLocale);

        return format.format(d);
    }

    public static final String getMonthYearString(String date) {
        return date.substring(5, 7) + "/" + date.substring(0, 4);
    }

    public static final String getDayMonthYearTime(Date d) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm", brLocale);

        return format.format(d);
    }

    public static final String getMonth(Date d) {

        SimpleDateFormat format = new SimpleDateFormat("MMMM", brLocale);

        return format.format(d);
    }

    public static final int getMonthNumber(Date d) {

        SimpleDateFormat format = new SimpleDateFormat("MM", brLocale);

        String month = format.format(d);
        if (isStringValidNumber(month)) {
            return Integer.parseInt(month);
        }
        return 0;
    }

    public static final int getDayNumber(Date d) {

        SimpleDateFormat format = new SimpleDateFormat("dd", brLocale);

        String day = format.format(d);
        if (isStringValidNumber(day)) {
            return Integer.parseInt(day);
        }
        return 0;
    }

    public static String getPreviousMonth(){
        Calendar cal =  Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.add(Calendar.MONTH, -1);

        return new SimpleDateFormat("MMMM yyyy").format(cal.getTime());
    }

    public static String getPreviousDay(){
        Calendar cal =  Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.add(Calendar.DAY_OF_MONTH, -1);

        return new SimpleDateFormat("yyyy-MMM/dd").format(cal.getTime());
    }

    public static long lastSaturdayStamp(){
        Calendar cal= Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.add(Calendar.DAY_OF_WEEK, -(cal.get(Calendar.DAY_OF_WEEK)));

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTimeInMillis();
    }

    public static long sundayAWeekAgoStamp(){
        Calendar cal= Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.add( Calendar.DAY_OF_WEEK, -(cal.get(Calendar.DAY_OF_WEEK) + 6));

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTimeInMillis();
    }

    public static final int getHourNumber(Date d) {

        SimpleDateFormat format = new SimpleDateFormat("hh", brLocale);

        String day = format.format(d);
        if (isStringValidNumber(day)) {
            return Integer.parseInt(day);
        }
        return 0;
    }

    public static final String fromMilisecondsToTime(Double seconds){
        BigDecimal bd = new BigDecimal(seconds);
        bd = bd.setScale(0, BigDecimal.ROUND_DOWN);
//        bd = bd.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_DOWN);

        int intSeconds = bd.intValue();
        int minutes = 0;
        int hours = 0;

        if (intSeconds > 60){
            minutes = intSeconds / 60;

            if(minutes > 60){
                hours = minutes / 60;
                minutes = minutes % 60;
            }
            intSeconds = intSeconds % 60;

        }else{
            minutes = 0;
        }

        return (hours > 0 ? (hours >= 10 ? "" : "0") + hours + ":" : "") + (minutes >= 10 ? "" : "0") + minutes + ":" + (intSeconds >= 10 ? "" : "0") + intSeconds;
    }

    public static final int getMinuteNumber(Date d) {

        SimpleDateFormat format = new SimpleDateFormat("mm", brLocale);

        String day = format.format(d);
        if (isStringValidNumber(day)) {
            return Integer.parseInt(day);
        }
        return 0;
    }

    public static final String getYear(Date d) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy", brLocale);

        return format.format(d);
    }

    static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String GetUTCdatetimeAsString() {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String utcTime = sdf.format(new Date());

        return utcTime;
    }

    public static String getTimePassed(Date date, Context context){
        String lastUpdate = "-";
        if (date != null){
            long lastUpdateTimeStamp = DateUtil.getDateTimeStamp(date) / 1000;

            Date current = stringToDate(GetUTCdatetimeAsString());

            long currentTimeStamp = getDateTimeStamp(current) / 1000;

            long diffTimeStamp = currentTimeStamp - lastUpdateTimeStamp;

            if (diffTimeStamp < 0) {
                diffTimeStamp = 0;
            }

            if (diffTimeStamp < 60){
                if (diffTimeStamp >= 10){
                    lastUpdate = diffTimeStamp + "s";
                }else{
                    lastUpdate = "0"+ diffTimeStamp + "s";
                }
            }else if (diffTimeStamp >= 60){
                BigDecimal bd = new BigDecimal(diffTimeStamp);
                bd = bd.setScale(0, BigDecimal.ROUND_DOWN);
                bd = bd.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);
                bd = bd.setScale(0, BigDecimal.ROUND_DOWN);
                int minutes = bd.intValue();

                if (minutes < 60) {
                    if (minutes >= 10){
                        lastUpdate = minutes + "m";
                    }else{
                        lastUpdate = "0" + minutes + "m";
                    }
                }else{
                    bd = new BigDecimal(minutes);
                    bd = bd.setScale(0, BigDecimal.ROUND_DOWN);
                    bd = bd.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);
                    bd = bd.setScale(0, BigDecimal.ROUND_DOWN);
                    int hours = bd.intValue();

                    if (hours < 24){

                        if (hours >= 10){
                            lastUpdate = hours + "h";
                        }else{
                            lastUpdate = "0" + hours + "h";
                        }

                        minutes = minutes - hours * 60;
                        if (minutes >= 10){
                            lastUpdate = lastUpdate + minutes + "m";
                        }else{
                            lastUpdate = lastUpdate + "0" + minutes + "m";
                        }
                    }else{
                        bd = new BigDecimal(hours);
                        bd = bd.setScale(0, BigDecimal.ROUND_DOWN);
                        bd = bd.divide(new BigDecimal(24), 2, BigDecimal.ROUND_HALF_UP);
                        bd = bd.setScale(0, BigDecimal.ROUND_DOWN);

                        int days = bd.intValue();

                        if (days < 30){
                            if (days >= 10){
                                lastUpdate = days + "d";
                            }else{
                                lastUpdate = "0" + days + "d";
                            }
                        }else{
                            bd = new BigDecimal(days);
                            bd = bd.setScale(0, BigDecimal.ROUND_DOWN);
                            bd = bd.divide(new BigDecimal(30), 2, BigDecimal.ROUND_HALF_UP);
                            bd = bd.setScale(0, BigDecimal.ROUND_DOWN);
                            int months = bd.intValue();

                            if (months < 12){
                                if (months >= 10){
                                    lastUpdate = months + "months";
                                }else{
                                    if (months == 1){
                                        lastUpdate = "0" + months + " month";
                                    }else{
                                        lastUpdate = "0" + months + " months";
                                    }
                                }
                            }else{
                                bd = new BigDecimal(months);
                                bd = bd.setScale(0, BigDecimal.ROUND_DOWN);
                                bd = bd.divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP);
                                bd = bd.setScale(0, BigDecimal.ROUND_DOWN);
                                int years = bd.intValue();

                                if (years >= 10){
                                    lastUpdate = years + "y";
                                }else{
                                    lastUpdate = "0" + years + "y";
                                }
                            }
                        }
                    }
                }
            }
        }

        return lastUpdate;
    }

    public static final int getYearNumber(Date d) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy", brLocale);

        String year = format.format(d);
        if (isStringValidNumber(year)) {
            return Integer.parseInt(year);
        }
        return 0;
    }

    public static final Date stringToDate(String dateStr) {
        for (String dateFmt : DATE_FORMATS) {
            try {
                SimpleDateFormat format = new SimpleDateFormat(dateFmt,
                        brLocale);

//                format.setTimeZone(TimeZone.getTimeZone("UTC"));

                dateStr = format.format(format.parse(dateStr));

                return format.parse(dateStr);
            } catch (Exception e) {}
        }
        return null;
    }

    public static final Date stringToDateLocalTimezone(String dateStr) {
        for (String dateFmt : DATE_FORMATS) {
            try {
                SimpleDateFormat format = new SimpleDateFormat(dateFmt,
                        brLocale);

                dateStr = format.format(format.parse(dateStr));

                return format.parse(dateStr);
            } catch (Exception e) {}
        }
        return null;
    }

    public static int fromMonthToNumber(String monthName){
        Date date = null;
        try {
            date = new SimpleDateFormat("MMMM").parse(monthName);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC")
        );
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static String getCurrentTimeStampString() {
        return String.valueOf(getCurrentTimeStamp());
    }
}
