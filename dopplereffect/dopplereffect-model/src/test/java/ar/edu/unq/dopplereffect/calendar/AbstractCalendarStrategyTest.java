package ar.edu.unq.dopplereffect.calendar;

import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_05;
import junit.framework.Assert;

import org.junit.Test;

public abstract class AbstractCalendarStrategyTest {

    protected abstract CalendarStrategy getWeekday();

    @Test
    public void testNext() {
        final CalendarStrategy weekday = this.getWeekday();
        Assert.assertEquals("", D_2011_04_05, weekday.getDay());
        final int totalDays = weekday.getTotalDays();
        weekday.next();
        Assert.assertEquals("Fallo en avanzar al siguiente ", D_2011_04_05.plusDays(totalDays), weekday.getDay());
    }

    @Test
    public void testPlus() {
        final CalendarStrategy weekday = this.getWeekday();
        Assert.assertEquals("", D_2011_04_05, weekday.getDay());
        weekday.plus();
        Assert.assertEquals("Fallo al sumar los dias", D_2011_04_05.plusDays(1), weekday.getDay());
    }

}
