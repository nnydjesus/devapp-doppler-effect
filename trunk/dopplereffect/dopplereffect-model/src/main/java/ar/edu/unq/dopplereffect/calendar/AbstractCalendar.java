package ar.edu.unq.dopplereffect.calendar;

public abstract class AbstractCalendar implements ICalendar {

    private CalendarStrategy strategy;

    public AbstractCalendar(final CalendarStrategy strategy) {
        this.strategy = strategy;
    }

    public void next() {
        this.getStrategy().next();
    }

    public void setStrategy(final CalendarStrategy strategy) {
        this.strategy = strategy;
    }

    public CalendarStrategy getStrategy() {
        return strategy;
    }

}
