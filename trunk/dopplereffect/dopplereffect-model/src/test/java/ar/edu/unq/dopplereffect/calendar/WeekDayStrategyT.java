package ar.edu.unq.dopplereffect.calendar;

import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_05;

/**
 * La idea era llamarla WeekDayStrategyTest pero PMD chilla porque contiene la
 * Palabra Test y no tiene ningun assert
 */
public class WeekDayStrategyT extends AbstractCalendarStrategyTest {

    @Override
    protected WeekdayStrategy getWeekday() {
        return new WeekdayStrategy(D_2011_04_05);
    }

}
