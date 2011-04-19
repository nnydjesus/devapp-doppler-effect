package ar.edu.unq.dopplereffect.calendar;

import static ar.edu.unq.dopplereffect.bean.DateHelpers.*;
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
