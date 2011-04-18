package ar.edu.unq.dopplereffect.leaverequests;

import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_01;
import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_05;
import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_06;
import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_08;
import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_09;
import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_11;
import static ar.edu.unq.dopplereffect.bean.DateHelpers.getDates;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Assert;
import org.junit.Test;

public class IntervalDurationStrategyTest {

    @Test
    public void testIncludesDay() {
        IntervalDurationStrategy strategy = new IntervalDurationStrategy(new Interval(D_2011_04_05, D_2011_04_09));
        for (DateTime date : getDates(D_2011_04_05, D_2011_04_09)) {
            Assert.assertTrue("", strategy.includesDay(date));
        }
    }

    @Test
    public void testDontIncludesDay() {
        IntervalDurationStrategy strategy = new IntervalDurationStrategy(new Interval(D_2011_04_06, D_2011_04_08));
        Assert.assertFalse("", strategy.includesDay(D_2011_04_05));
        Assert.assertFalse("", strategy.includesDay(D_2011_04_09));
    }

    @Test
    public void testGetAmountOfDays() {
        IntervalDurationStrategy strategy = new IntervalDurationStrategy(new Interval(D_2011_04_05, D_2011_04_08));
        Assert.assertEquals("La cantidad de dias fallo", 4, strategy.getAmountOfDays());
    }

    @Test
    public void testOverlapsInterval() {
        IntervalDurationStrategy strategy = new IntervalDurationStrategy(new Interval(D_2011_04_06, D_2011_04_08));
        Assert.assertTrue("", strategy.overlapsInterval(new Interval(D_2011_04_01, D_2011_04_11)));
        Assert.assertTrue("", strategy.overlapsInterval(new Interval(D_2011_04_01, D_2011_04_06)));
        Assert.assertTrue("", strategy.overlapsInterval(new Interval(D_2011_04_08, D_2011_04_11)));
    }

    @Test
    public void testDoesntOverlapInterval() {
        IntervalDurationStrategy strategy = new IntervalDurationStrategy(new Interval(D_2011_04_06, D_2011_04_08));
        Assert.assertFalse("", strategy.overlapsInterval(new Interval(D_2011_04_01, D_2011_04_05)));
        Assert.assertFalse("", strategy.overlapsInterval(new Interval(D_2011_04_09, D_2011_04_11)));
    }

    @Test
    public void testGetYear() {
        IntervalDurationStrategy strategy = new IntervalDurationStrategy(new Interval(D_2011_04_06, D_2011_04_08));
        Assert.assertEquals("La cantidad de dias fallo", 2011, strategy.getYear());
    }
}
