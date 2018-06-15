package cpny.korc.korcapp.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cpny.korc.korcapp.helpers.StringHelper;

/**
 * Created by Arquia on 17/03/2018.
 */

public  class DateService {

    public static int GetNumberOfDayInWeek(Calendar cal)
    {
       int dayOfWeek= cal.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek==1)
            dayOfWeek=6;
        else
            dayOfWeek=dayOfWeek-2;

        return dayOfWeek;
    }

    public static int  GetNumberOfWeek(String day,String month, String year) {
        String input = year + StringHelper.padLeft(month,2,'0')  + StringHelper.padLeft(day,2,'0');
        String format = "yyyyMMdd";
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            Date date = df.parse(input);
            Calendar calendar =Calendar.getInstance();
            calendar.setTime(date);
            return GetNumberOfWeek(calendar);
        }catch(ParseException ex){
            return 0;
        }
    }

        public static int GetNumberOfWeek(Calendar calendar)
    {
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        // int day = GetCurrentWeekDay();
        return week;
    }
}
