package ar.edu.unq.dopplereffect.calendar;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Test;

public abstract class AbstractCalendarStrategyTest {

    protected abstract CalendarStrategy getWeekday();

    private static DateTime date;

    public AbstractCalendarStrategyTest(final DateTime aDate) {
        date = aDate;
    }

    @Test
    public void testNext() {
        final CalendarStrategy weekday = this.getWeekday();
        Assert.assertEquals("", date, weekday.getDay());
        final int totalDays = weekday.getTotalDays();
        weekday.next();
        Assert.assertEquals("Fallo en avanzar al siguiente ", date.plusDays(totalDays), weekday.getDay());
    }

    @Test
    public void testPlus() {
        final CalendarStrategy weekday = this.getWeekday();
        Assert.assertEquals("", date, weekday.getDay());
        weekday.plus();
        Assert.assertEquals("Fallo al sumar los dias", date.plusDays(1), weekday.getDay());
    }

}
