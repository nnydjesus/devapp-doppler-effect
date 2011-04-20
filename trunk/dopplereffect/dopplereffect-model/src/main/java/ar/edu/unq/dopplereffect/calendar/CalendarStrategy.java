package ar.edu.unq.dopplereffect.calendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

public abstract class CalendarStrategy {

    /* ************************ INSTANCE VARIABLES ************************ */

    private DateTime day;

    /* *************************** CONSTRUCTORS *************************** */
    public CalendarStrategy(final int year, final int month, final int day) {
        this(new DateTime(year, month, day, 0, 0, 0, 0));
    }

    public CalendarStrategy(final Years year, final Months month, final Days day) {
        this(year.getYears(), month.getMonths(), day.getDays());
    }

    public CalendarStrategy(final DateTime date) {
        day = new DateTime(date);
    }

    /* **************************** OPERATIONS **************************** */

    public DateTime next() {
        day = this.internalNext();
        return this.getDay();
    }

    public void plus() {
        day = day.plusDays(1);
    }

    /*
     *  **************************** ABSTRACT METHODS
     * *****************************
     */

    public abstract DateTime internalNext();

    public abstract CalendarStrategy cloneStrategy();

    public abstract int getTotalDays();

    /* **************************** ACCESSORS ***************************** */

    protected void setDay(final DateTime day) {
        this.day = day;
    }

    public DateTime getDay() {
        return day;
    }
}
