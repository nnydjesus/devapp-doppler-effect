package ar.edu.unq.dopplereffect.calendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

public class MonthWrapper extends CalendarWrapper {

    public MonthWrapper(final Years year, final Months month, final Days day) {
        super(year, month, day);
    }

    @Override
    public DateTime internalNext() {
        return this.getDay().plusMonths(1);
    }

}
