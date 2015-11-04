package com.cook.simplerealmandroid.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by roma on 04.11.15.
 */
public class DateFormatter {

    private static String DATE_PATTERN = "mm/dd/yyyy";

    public static String convertDateToString(Date date){
        return new SimpleDateFormat(DATE_PATTERN).format(date);
    }
}
