package de.tuchemnitz.de.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common_code {

    static SimpleDateFormat sdf;

    public static String getCurrentDate(){
        sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        Date d = new Date();
        return sdf.format(d);
    }

    public static String convertDateToString(Date date){
        sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        Date d = date;
        return sdf.format(d);
    }
}
