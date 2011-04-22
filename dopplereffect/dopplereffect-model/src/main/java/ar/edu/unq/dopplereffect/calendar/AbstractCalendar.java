package ar.edu.unq.dopplereffect.calendar;

public abstract class AbstractCalendar implements ICalendar {

    /* ************************ INSTANCE VARIABLES ************************ */

    private CalendarStrategy strategy;

    /* *************************** CONSTRUCTORS *************************** */

    public AbstractCalendar(final CalendarStrategy strategy) {
        this.strategy = strategy;
    }

    /* **************************** OPERATIONS **************************** */

    public void next() {
        this.getStrategy().next();
    }

    /* **************************** ACCESSORS ***************************** */

    public void setStrategy(final CalendarStrategy strategy) {
        this.strategy = strategy;
    }

    public CalendarStrategy getStrategy() {
        return strategy;
    }
}
