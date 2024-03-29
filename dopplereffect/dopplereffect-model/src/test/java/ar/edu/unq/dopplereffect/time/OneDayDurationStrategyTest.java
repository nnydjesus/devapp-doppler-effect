package ar.edu.unq.dopplereffect.time;

import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_05;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_06;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_08;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_09;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_11;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.getDates;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Test;

public class OneDayDurationStrategyTest {

    private OneDayDurationStrategy createOneDayDurationStrategy(final DateTime date) {
        return new OneDayDurationStrategy(date);
    }

    @Test
    public void testIncludesDay() {
        OneDayDurationStrategy strategy = this.createOneDayDurationStrategy(D_2011_04_09);
        assertTrue("la licencia deberia incluir su unico dia", strategy.includesDay(D_2011_04_09));
    }

    @Test
    public void testDontIncludesDay() {
        OneDayDurationStrategy strategy = this.createOneDayDurationStrategy(D_2011_04_08);
        assertTrue("la licencia NO deberia incluir el dia", strategy.includesDay(D_2011_04_08));
        assertFalse("la licencia NO deberia incluir el dia", strategy.includesDay(D_2011_04_11));
    }

    @Test
    public void testGetAmountOfDays() {
        OneDayDurationStrategy any = this.createOneDayDurationStrategy(D_2011_04_09);
        assertEquals("la cantidad de dias debe ser 1, siempre ", 1, any.getAmountOfDays());
    }

    @Test
    public void testOverlapsInterval() {
        Interval interv = new Interval(D_2011_04_08, D_2011_04_11);
        OneDayDurationStrategy strategy;
        for (DateTime date : getDates(D_2011_04_08, D_2011_04_11)) {
            strategy = this.createOneDayDurationStrategy(date);
            assertTrue("la duracion de la licencia deberia superponerse con el intervalo",
                    strategy.overlapsInterval(interv));
        }
    }

    @Test
    public void testDoesntOverlapInterval() {
        Interval interval = new Interval(D_2011_04_06, D_2011_04_08);
        OneDayDurationStrategy strategy1 = this.createOneDayDurationStrategy(D_2011_04_05);
        OneDayDurationStrategy strategy2 = this.createOneDayDurationStrategy(D_2011_04_09);
        assertFalse("la duracion de la licencia NO deberia superponerse con el intervalo",
                strategy1.overlapsInterval(interval));
        assertFalse("la duracion de la licencia NO deberia superponerse con el intervalo",
                strategy2.overlapsInterval(interval));
    }

    @Test
    public void testGetYear() {
        OneDayDurationStrategy strategy = this.createOneDayDurationStrategy(D_2011_04_09);
        assertEquals("el año deberia ser 2011", 2011, strategy.getYear());
    }

    @Test
    public void testOverlapsWithOneDayLeaveRequest() {
        OneDayDurationStrategy strategy1 = this.createOneDayDurationStrategy(D_2011_04_05);
        OneDayDurationStrategy strategy2 = this.createOneDayDurationStrategy(D_2011_04_05);
        assertTrue("las duraciones deben superponerse, son del mismo dia", strategy1.overlapsInterval(strategy2));
    }

    @Test
    public void testDoesntOverlapWithOneDayLeaveRequest() {
        OneDayDurationStrategy strategy1 = this.createOneDayDurationStrategy(D_2011_04_06);
        OneDayDurationStrategy strategy2 = this.createOneDayDurationStrategy(D_2011_04_05);
        OneDayDurationStrategy strategy3 = this.createOneDayDurationStrategy(D_2011_04_08);
        assertFalse("las duraciones no deben superponerse, son de dias diferentes",
                strategy1.overlapsInterval(strategy2));
        assertFalse("las duraciones no deben superponerse, son de dias diferentes",
                strategy1.overlapsInterval(strategy3));
    }

    @Test
    public void testOverlapsWithIntervalLeaveRequest() {
        OneDayDurationStrategy oneDayStrategy = this.createOneDayDurationStrategy(D_2011_04_06);
        IntervalDurationStrategy intervalStrategy = mock(IntervalDurationStrategy.class);
        oneDayStrategy.overlapsInterval(intervalStrategy);
        verify(intervalStrategy).overlapsInterval(oneDayStrategy);
    }

    @Test
    public void testGetSuperpositionDays() {
        OneDayDurationStrategy ods = this.createOneDayDurationStrategy(D_2011_04_08);
        IntervalDurationStrategy ids = mock(IntervalDurationStrategy.class);
        when(ids.includesDay(D_2011_04_08)).thenReturn(true);
        assertEquals("la superposicion de dias fallo", 1, ods.getSuperpositionDaysWith(ids));
    }

    @Test
    public void testGetSuperpositionDaysZeroResult() {
        OneDayDurationStrategy ods = this.createOneDayDurationStrategy(D_2011_04_11);
        IntervalDurationStrategy ids = mock(IntervalDurationStrategy.class);
        when(ids.includesDay(D_2011_04_11)).thenReturn(false);
        assertEquals("la superposicion de dias fallo", 0, ods.getSuperpositionDaysWith(ids));
    }
}