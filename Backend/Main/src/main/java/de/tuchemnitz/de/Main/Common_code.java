package de.tuchemnitz.de.Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Common_code {

    static SimpleDateFormat sdf;
    public static final String REST_SERVICE_URI = "http://localhost:8080/";

    public static String getCurrentDate(){
        sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        Date d = new Date();
        return sdf.format(d);
    }
    public static Date getCurrentDateinDate() throws ParseException {
        sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        return sdf.parse(getCurrentDate());
    }

    public static String convertDateToString(Date date){
        sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        Date d = date;
        return sdf.format(d);
    }
    public static Date convertStringToDate(String date) throws Exception{
        sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        return sdf.parse(date);
    }
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    public static Date addMinutes(Date date, int minutes){

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return  cal.getTime();
    }

//    public static String compareDates(String date1, String date2) throws Exception {
//        sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
//        Date d1 = convertStringToDate(date1);
//        Date d2 = convertStringToDate(date2);
//
//        if(d1.after())
//    }
}
