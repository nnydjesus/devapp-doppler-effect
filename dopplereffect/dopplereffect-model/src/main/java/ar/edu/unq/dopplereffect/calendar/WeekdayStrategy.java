package ar.edu.unq.dopplereffect.calendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

public class WeekdayStrategy extends CalendarStrategy {

    public WeekdayStrategy(final Years year, final Months month, final Days day) {
        super(year, month, day);
    }

    public WeekdayStrategy(final DateTime day) {
        super(day);
    }

    @Override
    public DateTime internalNext() {
        return this.getDay().plusDays(7);
    }

    @Override
    public CalendarStrategy cloneStrategy() {
        return new WeekdayStrategy(new DateTime(this.getDay()));
    }

    @Override
    public int getTotalDays() {
        return 7;
    }

}
