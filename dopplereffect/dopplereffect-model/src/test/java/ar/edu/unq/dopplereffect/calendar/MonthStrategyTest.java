package ar.edu.unq.dopplereffect.calendar;

import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_05;

public class MonthStrategyTest extends AbstractCalendarStrategyTest {

    @Override
    protected CalendarStrategy getWeekday() {
        return new MonthStrategy(D_2011_04_05);
    }

}
