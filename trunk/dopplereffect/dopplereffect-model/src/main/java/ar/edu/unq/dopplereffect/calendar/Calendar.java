package ar.edu.unq.dopplereffect.calendar;

public class Calendar {

    private ICalendar strategy;

    public void setStrategy(final ICalendar strategy) {
        this.strategy = strategy;
    }

    public ICalendar getStrategy() {
        return strategy;
    }

}
