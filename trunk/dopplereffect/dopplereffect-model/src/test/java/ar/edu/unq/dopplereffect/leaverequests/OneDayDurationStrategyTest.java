package ar.edu.unq.dopplereffect.leaverequests;

import static ar.edu.unq.dopplereffect.bean.DateHelpers.*;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class OneDayDurationStrategyTest {

    @Test
    public void testIncludesDay() {
        OneDayDurationStrategy strategy = new OneDayDurationStrategy(D_2011_04_09);
        Assert.assertTrue("la licencia deberia incluir su unico dia", strategy.includesDay(D_2011_04_09));
    }

    @Test
    public void testDontIncludesDay() {
        OneDayDurationStrategy strategy = new OneDayDurationStrategy(D_2011_04_08);
        Assert.assertTrue("la licencia NO deberia incluir el dia", strategy.includesDay(D_2011_04_08));
        Assert.assertFalse("la licencia NO deberia incluir el dia", strategy.includesDay(D_2011_04_11));
    }

    @Test
    public void testGetAmountOfDays() {
        OneDayDurationStrategy any = new OneDayDurationStrategy(D_2011_04_09);
        Assert.assertEquals("la cantidad de dias debe ser 1, siempre ", 1, any.getAmountOfDays());
    }

    @Test
    public void testOverlapsInterval() {
        Interval interv = new Interval(D_2011_04_08, D_2011_04_11);
        for (DateTime date : getDates(D_2011_04_08, D_2011_04_11)) {
            OneDayDurationStrategy strategy = new OneDayDurationStrategy(date);
            Assert.assertTrue("la duracion de la licencia deberia superponerse con el intervalo",
                    strategy.overlapsInterval(interv));
        }
    }

    @Test
    public void testDoesntOverlapInterval() {
        Interval interval = new Interval(D_2011_04_06, D_2011_04_08);
        OneDayDurationStrategy strategy1 = new OneDayDurationStrategy(D_2011_04_05);
        OneDayDurationStrategy strategy2 = new OneDayDurationStrategy(D_2011_04_09);
        Assert.assertFalse("la duracion de la licencia NO deberia superponerse con el intervalo",
                strategy1.overlapsInterval(interval));
        Assert.assertFalse("la duracion de la licencia NO deberia superponerse con el intervalo",
                strategy2.overlapsInterval(interval));
    }

    @Test
    public void testGetYear() {
        OneDayDurationStrategy strategy = new OneDayDurationStrategy(D_2011_04_09);
        Assert.assertEquals("el año deberia ser 2011", 2011, strategy.getYear());
    }

    @Test
    public void testOverlapsWithOneDayLeaveRequest() {
        OneDayDurationStrategy strategy1 = new OneDayDurationStrategy(D_2011_04_05);
        OneDayDurationStrategy strategy2 = new OneDayDurationStrategy(D_2011_04_05);
        Assert.assertTrue("las duraciones deben superponerse, son del mismo dia", strategy1.overlapsInterval(strategy2));
    }

    @Test
    public void testDoesntOverlapWithOneDayLeaveRequest() {
        OneDayDurationStrategy strategy1 = new OneDayDurationStrategy(D_2011_04_06);
        OneDayDurationStrategy strategy2 = new OneDayDurationStrategy(D_2011_04_05);
        OneDayDurationStrategy strategy3 = new OneDayDurationStrategy(D_2011_04_08);
        Assert.assertFalse("las duraciones no deben superponerse, son de dias diferentes",
                strategy1.overlapsInterval(strategy2));
        Assert.assertFalse("las duraciones no deben superponerse, son de dias diferentes",
                strategy1.overlapsInterval(strategy3));
    }

    @Test
    @SuppressWarnings("PMD")
    public void testOverlapsWithIntervalLeaveRequest() {
        OneDayDurationStrategy oneDayStrategy = new OneDayDurationStrategy(D_2011_04_06);
        IntervalDurationStrategy intervalStrategy = Mockito.mock(IntervalDurationStrategy.class);
        oneDayStrategy.overlapsInterval(intervalStrategy);
        Mockito.verify(intervalStrategy).overlapsInterval(oneDayStrategy);
    }
}
