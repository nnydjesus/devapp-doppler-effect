package ar.edu.unq.dopplereffect.calendar;

import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_01;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_10;

/**
 * La idea era llamarla MontStrategyTest pero PMD chilla porque contiene la
 * Palabra Test y no tiene ningun assert
 */
public class MonthStrategyT extends AbstractCalendarStrategyTest {

    public MonthStrategyT() {
        super(D_2011_04_01);
    }

    @Override
    protected CalendarStrategy getWeekday() {
        return new MonthStrategy(D_2011_04_10);
    }

}
