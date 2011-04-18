package ar.edu.unq.dopplereffect.leaverequests;

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

public class OneDurationStrategyTest {

    @Test
    public void testIncludesDay() {
        OneDayDurationStrategy strategy = new OneDayDurationStrategy(D_2011_04_09);
        Assert.assertTrue("", strategy.includesDay(D_2011_04_09));
    }

    @Test
    public void testDontIncludesDay() {
        OneDayDurationStrategy strategy = new OneDayDurationStrategy(D_2011_04_08);
        Assert.assertTrue("", strategy.includesDay(D_2011_04_08));
        Assert.assertFalse("", strategy.includesDay(D_2011_04_11));
    }

    @Test
    public void testGetAmountOfDays() {
        OneDayDurationStrategy strategy = new OneDayDurationStrategy(D_2011_04_09);
        Assert.assertEquals("", 1, strategy.getAmountOfDays());
    }

    @Test
    public void testOverlapsInterval() {
        Interval interv = new Interval(D_2011_04_08, D_2011_04_11);
        for (DateTime date : getDates(D_2011_04_08, D_2011_04_11)) {
            OneDayDurationStrategy strategy = new OneDayDurationStrategy(date);
            Assert.assertTrue("", strategy.overlapsInterval(interv));
        }
    }

    @Test
    public void testDoesntOverlapInterval() {
        Interval interval = new Interval(D_2011_04_06, D_2011_04_08);
        OneDayDurationStrategy strategy1 = new OneDayDurationStrategy(D_2011_04_05);
        OneDayDurationStrategy strategy2 = new OneDayDurationStrategy(D_2011_04_09);
        Assert.assertFalse("", strategy1.overlapsInterval(interval));
        Assert.assertFalse("", strategy2.overlapsInterval(interval));
    }

    @Test
    public void testGetYear() {
        OneDayDurationStrategy strategy = new OneDayDurationStrategy(D_2011_04_09);
        Assert.assertEquals("", 2011, strategy.getYear());
    }

    @Test
    public void testOverlapsWithOneDayLeaveRequest() {
        OneDayDurationStrategy strategy1 = new OneDayDurationStrategy(D_2011_04_05);
        OneDayDurationStrategy strategy2 = new OneDayDurationStrategy(D_2011_04_05);
        Assert.assertTrue("", strategy1.overlapsWithOneDayDuration(strategy2));
    }

    @Test
    public void testDoesntOverlapWithOneDayLeaveRequest() {
        OneDayDurationStrategy strategy1 = new OneDayDurationStrategy(D_2011_04_06);
        OneDayDurationStrategy strategy2 = new OneDayDurationStrategy(D_2011_04_05);
        OneDayDurationStrategy strategy3 = new OneDayDurationStrategy(D_2011_04_08);
        Assert.assertFalse("", strategy1.overlapsWithOneDayDuration(strategy2));
        Assert.assertFalse("", strategy1.overlapsWithOneDayDuration(strategy3));
    }

    // @Test
    // public void testOverlapsWithIntervalLeaveRequest() {
    //
    // }
}
