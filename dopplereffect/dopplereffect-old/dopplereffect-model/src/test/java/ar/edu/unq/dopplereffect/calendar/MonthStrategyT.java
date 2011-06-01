package ar.edu.unq.dopplereffect.calendar;

import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_05;

/**
 * La idea era llamarla MontStrategyTest pero PMD chilla porque contiene la
 * Palabra Test y no tiene ningun assert
 */
public class MonthStrategyT extends AbstractCalendarStrategyTest {

    @Override
    protected CalendarStrategy getWeekday() {
        return new MonthStrategy(D_2011_04_05);
    }

}
