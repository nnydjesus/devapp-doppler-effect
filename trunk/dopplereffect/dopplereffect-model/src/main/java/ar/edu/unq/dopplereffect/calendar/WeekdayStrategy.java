package ar.edu.unq.dopplereffect.calendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

public class WeekdayStrategy extends CalendarStrategy {

    /* ************************ INSTANCE VARIABLES ************************ */

    /* *************************** CONSTRUCTORS *************************** */

    public WeekdayStrategy(final Years year, final Months month, final Days day) {
        super(year, month, day);
    }

    public WeekdayStrategy(final int year, final int month, final int days) {
        this(Years.years(year), Months.months(month), Days.days(days));
    }

    public WeekdayStrategy(final DateTime day) {
        super(day);
    }

    /* **************************** OPERATIONS **************************** */

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

    @Override
    public DateTime internalPrevious() {
        return this.getDay().minusDays(7);
    }

    @Override
    protected DateTime stabilize(final DateTime date) {
        DateTime currentDay = date;
        if (currentDay.getDayOfWeek() > 1) {
            currentDay = currentDay.minusDays(currentDay.getDayOfWeek() - 1);
        }
        return currentDay;
    }

    /* **************************** ACCESSORS ***************************** */
}
