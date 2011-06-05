package ar.edu.unq.dopplereffect.calendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

public class MonthStrategy extends CalendarStrategy {

    /* ************************ INSTANCE VARIABLES ************************ */

    /* *************************** CONSTRUCTORS *************************** */

    public MonthStrategy(final Years year, final Months month) {
        super(year, month, Days.ONE);
    }

    public MonthStrategy(final int year, final int month) {
        this(Years.years(year), Months.months(month));
    }

    // public MonthStrategy(final Years year, final Months month, final Days
    // day) {
    // this(year, month);
    // }

    public MonthStrategy(final DateTime day) {
        super(day);
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    public DateTime internalNext() {
        return this.getDay().plusMonths(1);
    }

    @Override
    public CalendarStrategy cloneStrategy() {
        return new MonthStrategy(this.getDay());
    }

    @Override
    public int getTotalDays() {
        return this.getDay().dayOfMonth().getMaximumValue();
    }

    @Override
    public DateTime internalPrevious() {
        return this.getDay().minusMonths(1);
    }

    @Override
    protected DateTime stabilize(final DateTime date) {
        DateTime currentDay = date;
        if (date.getDayOfMonth() > 1) {
            currentDay = currentDay.minusDays(currentDay.getDayOfMonth() - 1);
        }
        return currentDay;
    }

    /* **************************** ACCESSORS ***************************** */

}
