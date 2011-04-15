package ar.edu.unq.dopplereffect.calendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

public abstract class CalendarWrapper {

    private DateTime day;

    public CalendarWrapper(final Years year, final Months month, final Days day) {
        this.day = new DateTime(year.getYears(), month.getMonths(), day.getDays(), 0, 0, 0, 0);
    }

    public DateTime next() {
        day = this.internalNext();
        return day;
    }

    public abstract DateTime internalNext();

    public DateTime getDay() {
        return day;
    }

}
