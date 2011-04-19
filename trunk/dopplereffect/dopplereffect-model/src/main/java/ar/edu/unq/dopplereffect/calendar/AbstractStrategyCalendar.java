package ar.edu.unq.dopplereffect.calendar;

public abstract class AbstractStrategyCalendar implements StrategyCalendar {

    private CalendarWrapper weekday;

    public AbstractStrategyCalendar(final CalendarWrapper week) {
        weekday = week;
    }

    public void next() {
        weekday.next();
    }

    public void setWeekday(final CalendarWrapper weekday) {
        this.weekday = weekday;
    }

    public CalendarWrapper getWeekday() {
        return weekday;
    }

}
