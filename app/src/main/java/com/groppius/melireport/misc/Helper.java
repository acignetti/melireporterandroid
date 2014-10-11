package com.groppius.melireport.misc;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by julio on 11/10/14.
 */
public class Helper {

    public static Date dateFormatter(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        Date date = new Date();
        try {
            date = formatter.parse(dateInString);
            Log.d("HELPER", date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
