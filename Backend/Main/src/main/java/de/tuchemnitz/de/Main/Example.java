package de.tuchemnitz.de.Main;

import java.util.Calendar;
import java.util.Date;

public class Example {
    public static void main(String[] args) {

        String date = Common_code.getCurrentDate();

        try {
//            Date d = Common_code.convertStringToDate(date);
//            Date dafter = addOneMonth(d);
//            System.out.println(Common_code.convertDateToString(dafter));
//            System.out.println(Common_code.convertDateToString(addOneDay(d, 32)));

            Date ago = Common_code.convertStringToDate("Sat, 3 Jul 2019 15:42:38 CEST");

//            if (ago.before())

            long difference = Common_code.getCurrentDateinDate().getTime() - ago.getTime();
            int some = (int) difference;
            System.out.println("Seconds " + some/1000);

            some = some/1000;
            some = some /60;
            System.out.println("Minutues: " + some);

//            System.out.println(daysBetween(ago, Common_code.getCurrentDateinDate()));
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

    public static String daysBetween(Date createdDate, Date expiryDate) {

        Calendar createdDateCal = Calendar.getInstance();
        createdDateCal.clear();
        createdDateCal.setTime(createdDate);

        Calendar expiryDateCal = Calendar.getInstance();
        expiryDateCal.clear();
        expiryDateCal.setTime(expiryDate);


        long daysBetween = 0;
        while (createdDateCal.before(expiryDateCal)) {
            createdDateCal.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween+"";
    }
}
