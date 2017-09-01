package br.com.savingfoodscanner.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by brunolemgruber on 31/08/17.
 */

public final class DateTime {

    private static SimpleDateFormat simpleDateFormat;

    public static String formatToString(String format, Date date){
        simpleDateFormat = new SimpleDateFormat(format);
        String strDate = simpleDateFormat.format(date);
        return strDate;
    }
}
