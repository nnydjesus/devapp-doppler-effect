package ar.edu.unq.dopplereffect.calendar;

import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_05;

public class WeekDayStrategyTest extends AbstractCalendarStrategyTest {

    @Override
    protected WeekdayStrategy getWeekday() {
        return new WeekdayStrategy(D_2011_04_05);
    }

}
