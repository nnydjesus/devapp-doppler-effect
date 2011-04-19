package ar.edu.unq.dopplereffect.calendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

public abstract class CalendarWrapper {

    private DateTime day;

    private PrintDay printDay;

    public CalendarWrapper(final int year, final int month, final int day) {
        this(new DateTime(year, month, day, 0, 0, 0, 0));
    }

    public CalendarWrapper(final Years year, final Months month, final Days day) {
        this(year.getYears(), month.getMonths(), day.getDays());
    }

    public CalendarWrapper(final DateTime date) {
        day = date;
    }

    public DateTime next() {
        this.setDay(this.internalNext());
        return this.getDay();
    }

    public void plus() {
        day = day.plusDays(1);
    }

    public abstract DateTime internalNext();

    public abstract CalendarWrapper getCalendarDay();

    public abstract int getTotalDays();

    protected void setDay(final DateTime day) {
        this.day = day;
    }

    public DateTime getDay() {
        return day;
    }

    public void setPrintDay(final PrintDay printDay) {
        this.printDay = printDay;
    }

    public PrintDay getPrintDay() {
        return printDay;
    }

}
