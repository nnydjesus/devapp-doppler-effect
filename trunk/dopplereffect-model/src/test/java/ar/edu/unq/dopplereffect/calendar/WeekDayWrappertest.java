package ar.edu.unq.dopplereffect.calendar;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;
import org.junit.Test;

public class WeekDayWrappertest {

    private final Days day = Days.ONE;

    private final Months month = Months.ONE;

    private final Years year = Years.years(2010);

    private WeekdayWrapper getWeekday() {
        return new WeekdayWrapper(year, month, day);
    }

    // FIXME mejorar el test
    @Test
    public void testNext() {
        final WeekdayWrapper weekday = this.getWeekday();
        DateTime next;
        for (int i = 1; i < 3; i++) {
            next = weekday.next();
            Assert.assertSame("", day.getDays() + 7 * i, next.getDayOfMonth());
        }
    }

}
