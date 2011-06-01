package ar.edu.unq.dopplereffect.calendar;

import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_05;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_06;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_08;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_09;
import junit.framework.Assert;

import org.junit.Test;

/**
 */
public class PrintDayTest {

    @Test
    public void testPrintday() {
        Assert.assertEquals(WeekDayName.Tuesday + " 5/4", PrintDay.printDay(D_2011_04_05));
        Assert.assertEquals(WeekDayName.Wednesday + " 6/4", PrintDay.printDay(D_2011_04_06));
        Assert.assertEquals(WeekDayName.Friday + " 8/4", PrintDay.printDay(D_2011_04_08));
        Assert.assertEquals(WeekDayName.Saturday + " 9/4", PrintDay.printDay(D_2011_04_09));
    }

}
