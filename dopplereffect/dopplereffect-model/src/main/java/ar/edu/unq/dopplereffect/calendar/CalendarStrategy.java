package ar.edu.unq.dopplereffect.calendar;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

public abstract class CalendarStrategy implements Serializable {

    /* ************************ INSTANCE VARIABLES ************************ */

    private static final long serialVersionUID = -3215114029589833655L;

    private DateTime day;

    /* *************************** CONSTRUCTORS *************************** */

    public CalendarStrategy(final int year, final int month, final int day) {
        this(new DateTime(year, month, day, 0, 0, 0, 0));
    }

    public CalendarStrategy(final Years year, final Months month, final Days day) {
        this(year.getYears(), month.getMonths(), day.getDays());
    }

    public CalendarStrategy(final DateTime date) {
        day = this.stabilize(date);
    }

    protected abstract DateTime stabilize(final DateTime date);

    /* **************************** OPERATIONS **************************** */

    public DateTime next() {
        day = this.internalNext();
        return this.getDay();
    }

    public DateTime previous() {
        day = this.internalPrevious();
        return this.getDay();
    }

    public void plus() {
        day = day.plusDays(1);
    }

    /* ************************* ABSTRACT METHODS ************************* */

    public abstract DateTime internalNext();

    public abstract DateTime internalPrevious();

    public abstract CalendarStrategy cloneStrategy();

    public abstract int getTotalDays();

    /* **************************** ACCESSORS ***************************** */

    public void setDay(final DateTime day) {
        this.day = this.stabilize(day);
    }

    public DateTime getDay() {
        return day;
    }
}
