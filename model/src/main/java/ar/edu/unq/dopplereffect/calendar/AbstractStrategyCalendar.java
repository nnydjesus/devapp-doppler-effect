package ar.edu.unq.dopplereffect.calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractStrategyCalendar implements StrategyCalendar {

    private final static Logger logger = LoggerFactory.getLogger(WeekStrategyCalendar.class);

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
