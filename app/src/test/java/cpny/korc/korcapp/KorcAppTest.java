package cpny.korc.korcapp;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import cpny.korc.korcapp.services.DateService;

import static org.junit.Assert.assertEquals;

/**
 * Created by Arquia on 14/03/2018.
 */
public class KorcAppTest {

    @Test
    public void testDatesDays() throws Exception {

        Calendar cal =Calendar.getInstance();
        int day_of_week, day;
        cal.setFirstDayOfWeek(Calendar.MONDAY);

        //  monday 2018 Jun 11
        cal.set(2018, 5, 11);

        day_of_week = DateService.GetNumberOfDayInWeek((cal));
        day = cal.get(Calendar.DAY_OF_WEEK);
        Assert.assertEquals(day_of_week,0);
        Assert.assertEquals(day,Calendar.MONDAY);

        //  sunday 2018 Jun 17
        cal.set(2018, 5, 17);
        day_of_week = DateService.GetNumberOfDayInWeek((cal));
        day = cal.get(Calendar.DAY_OF_WEEK);
        Assert.assertEquals(day_of_week,6);
        Assert.assertEquals(day,Calendar.SUNDAY);

        //  wednesday 2018 Jun 27
        cal.set(2018, 5, 27);
        day_of_week = DateService.GetNumberOfDayInWeek((cal));
        day = cal.get(Calendar.DAY_OF_WEEK);
        Assert.assertEquals(day_of_week,2);
        Assert.assertEquals(day,Calendar.WEDNESDAY);
    }
    @Test
    public void testNumberOfWeek()throws Exception
    {
        String format = "yyyyMMdd";
        SimpleDateFormat df = new SimpleDateFormat(format);

        Date date = df.parse("20180104");
        int week = DateService.GetNumberOfWeek(date);
        Assert.assertEquals(week,1);

        date = df.parse("20180101");
        week = DateService.GetNumberOfWeek(date);
        Assert.assertEquals(week,1);

        date = df.parse("20180107");
        week = DateService.GetNumberOfWeek(date);
        Assert.assertEquals(week,1);

        date = df.parse("20180108");
        week = DateService.GetNumberOfWeek(date);
        Assert.assertEquals(week,2);
    }
}
