package de.tuchemnitz.de.Main;

import java.util.Calendar;
import java.util.Date;

public class Example {
    public static void main(String[] args) {

        String date = Common_code.getCurrentDate();

        try {
            Date d = Common_code.convertStringToDate(date);
            Date dafter = addOneMonth(d);
            System.out.println(Common_code.convertDateToString(dafter));
            System.out.println(Common_code.convertDateToString(addOneDay(d, 32)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Date addOneMonth(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }
    public static Date addOneDay(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

}
