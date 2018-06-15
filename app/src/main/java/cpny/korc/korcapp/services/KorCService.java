package cpny.korc.korcapp.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cpny.korc.korcapp.helpers.StringHelper;

public class KorCService {

    public enum KorCType
    {
        NOTHING,
        CUPS,
        KIDS
    }

    public KorCType GetKorCTypeDay(String day,String month, String year)
    {
        String input = year + StringHelper.padLeft(month,2,'0')  + StringHelper.padLeft(day,2,'0');
        String format = "yyyyMMdd";
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
            Date date = df.parse(input);
            return GetKorCTypeDay(date);

        } catch (ParseException e) {
            return KorCType.NOTHING;
        }
    }

    public KorCType GetKorCTypeDay(Date day)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        return GetKorCTypeDay(cal);
    }

    public KorCType GetKorCTypeDay(Calendar calendar)
    {

        int day = calendar.get(Calendar.DAY_OF_WEEK);
        {
            if (day!= Calendar.SATURDAY && day!=Calendar.SUNDAY) {
                return KorCType.NOTHING;
            }
        }

        int week = DateService.GetNumberOfWeek(calendar);
        if ((week%2)==0)
        {
            return KorCType.CUPS;
        }else
        {
            return KorCType.KIDS;
        }
    }
}
