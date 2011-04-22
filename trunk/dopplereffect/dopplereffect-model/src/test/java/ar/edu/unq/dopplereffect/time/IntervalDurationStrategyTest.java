package ar.edu.unq.dopplereffect.time;

import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_01;
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
import static org.mockito.Mockito.when;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Test;

public class IntervalDurationStrategyTest {

    @Test
    public void testIncludesDay() {
        IntervalDurationStrategy strategy = new IntervalDurationStrategy(new Interval(D_2011_04_05, D_2011_04_09));
        for (DateTime date : getDates(D_2011_04_05, D_2011_04_09)) {
            assertTrue("la duracion deberia incluir el dia", strategy.includesDay(date));
        }
    }

    @Test
    public void testDontIncludesDay() {
        IntervalDurationStrategy strategy = new IntervalDurationStrategy(new Interval(D_2011_04_06, D_2011_04_08));
        assertFalse("la duracion NO deberia incluir el dia", strategy.includesDay(D_2011_04_05));
        assertFalse("la duracion NO deberia incluir el dia", strategy.includesDay(D_2011_04_09));
    }

    @Test
    public void testGetAmountOfDays() {
        IntervalDurationStrategy strategy = new IntervalDurationStrategy(new Interval(D_2011_04_05, D_2011_04_08));
        assertEquals("La cantidad de dias fallo", 4, strategy.getAmountOfDays());
    }

    @Test
    public void testGetYear() {
        IntervalDurationStrategy strategy = new IntervalDurationStrategy(new Interval(D_2011_04_06, D_2011_04_08));
        assertEquals("La cantidad de dias fallo", 2011, strategy.getYear());
    }

    @Test
    public void testOverlapsInterval() {
        IntervalDurationStrategy strategy = new IntervalDurationStrategy(new Interval(D_2011_04_06, D_2011_04_08));
        assertTrue("los intervalos deberian superponerse",
                strategy.overlapsInterval(new Interval(D_2011_04_01, D_2011_04_11)));
        assertTrue("los intervalos deberian superponerse",
                strategy.overlapsInterval(new Interval(D_2011_04_01, D_2011_04_06)));
        assertTrue("los intervalos deberian superponerse",
                strategy.overlapsInterval(new Interval(D_2011_04_08, D_2011_04_11)));
    }

    @Test
    public void testDoesntOverlapInterval() {
        IntervalDurationStrategy strategy = new IntervalDurationStrategy(new Interval(D_2011_04_06, D_2011_04_08));
        assertFalse("los intervalos NO deberian superponerse",
                strategy.overlapsInterval(new Interval(D_2011_04_01, D_2011_04_05)));
        assertFalse("los intervalos NO deberian superponerse",
                strategy.overlapsInterval(new Interval(D_2011_04_09, D_2011_04_11)));
    }

    @Test
    public void testOverlapsWithOneDayDuration() {
        IntervalDurationStrategy intervalStrategy = new IntervalDurationStrategy(new Interval(D_2011_04_06,
                D_2011_04_08));
        OneDayDurationStrategy oneDayStrategy = mock(OneDayDurationStrategy.class);
        when(oneDayStrategy.getDate()).thenReturn(D_2011_04_06).thenReturn(D_2011_04_08);
        // los asserts son iguales, pero el mock retorna dos resultados
        // diferentes
        assertTrue("la duracion con intervalo deberia superponerse con la de un solo dia",
                intervalStrategy.overlapsInterval(oneDayStrategy));
        assertTrue("la duracion con intervalo deberia superponerse con la de un solo dia",
                intervalStrategy.overlapsInterval(oneDayStrategy));
    }

    @Test
    public void testOverlapsWithIntervalDurationIntersectingInTheEnd() {
        IntervalDurationStrategy req1 = new IntervalDurationStrategy(new Interval(D_2011_04_05, D_2011_04_08));
        IntervalDurationStrategy req2 = new IntervalDurationStrategy(new Interval(D_2011_04_08, D_2011_04_11));
        assertTrue("las duraciones deberian superponerse", req1.overlapsInterval(req2));
    }

    @Test
    public void testOverlapsWithIntervalDurationIntersectingSomeDays() {
        IntervalDurationStrategy strategy1 = new IntervalDurationStrategy(new Interval(D_2011_04_06, D_2011_04_11));
        IntervalDurationStrategy strategy2 = new IntervalDurationStrategy(new Interval(D_2011_04_05, D_2011_04_08));
        assertTrue("las duraciones deberian superponerse", strategy1.overlapsInterval(strategy2));
    }

    @Test
    public void testOverlapsWithIntervalDurationIntersectingAllDays() {
        IntervalDurationStrategy strategy1 = new IntervalDurationStrategy(new Interval(D_2011_04_06, D_2011_04_08));
        IntervalDurationStrategy strategy2 = new IntervalDurationStrategy(new Interval(D_2011_04_05, D_2011_04_11));
        assertTrue("las duraciones deberian superponerse", strategy1.overlapsInterval(strategy2));
    }

    @Test
    public void testOverlapsWithIntervalDurationDontIntersect() {
        IntervalDurationStrategy strategy1 = new IntervalDurationStrategy(new Interval(D_2011_04_05, D_2011_04_06));
        IntervalDurationStrategy strategy2 = new IntervalDurationStrategy(new Interval(D_2011_04_08, D_2011_04_11));
        assertFalse("las duraciones NO deberian superponerse", strategy1.overlapsInterval(strategy2));
    }
}