package ar.edu.unq.dopplereffect.calendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

public class MonthWrapper extends CalendarWrapper {

    public MonthWrapper(final Years year, final Months month) {
        super(year, month, Days.ONE);
        this.setPrintDay(new PrintMonthDay());
    }

    public MonthWrapper(final Years year, final Months month, final Days day) {
        this(year, month);
    }

    public MonthWrapper(final DateTime day) {
        super(day);
        this.setPrintDay(new PrintMonthDay());
    }

    @Override
    public DateTime internalNext() {
        return this.getDay().plusMonths(1);
    }

    @Override
    public CalendarWrapper getCalendarDay() {
        return new MonthWrapper(this.getDay());
    }

    @Override
    public int getTotalDays() {
        return this.getDay().dayOfMonth().getMaximumValue();
    }

}
