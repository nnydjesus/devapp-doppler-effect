package ar.edu.unq.dopplereffect.calendar;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Test;

public abstract class AbstractCalendarStrategyTest {

    protected abstract CalendarStrategy getWeekday();

    private static DateTime DATE;

    public AbstractCalendarStrategyTest(final DateTime date) {
        DATE = date;

    }

    @Test
    public void testNext() {
        final CalendarStrategy weekday = this.getWeekday();
        Assert.assertEquals("", DATE, weekday.getDay());
        final int totalDays = weekday.getTotalDays();
        weekday.next();
        Assert.assertEquals("Fallo en avanzar al siguiente ", DATE.plusDays(totalDays), weekday.getDay());
    }

    @Test
    public void testPlus() {
        final CalendarStrategy weekday = this.getWeekday();
        Assert.assertEquals("", DATE, weekday.getDay());
        weekday.plus();
        Assert.assertEquals("Fallo al sumar los dias", DATE.plusDays(1), weekday.getDay());
    }

}
