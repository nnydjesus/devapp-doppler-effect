package ar.edu.unq.dopplereffect.employees;

import junit.framework.Assert;

import org.junit.Test;

public class CareerPlanLevelTest {

    @Test
    public void testPreviousIsReciproc() {
        CareerPlanLevel cpl1 = new CareerPlanLevel("Senior");
        CareerPlanLevel cpl2 = new CareerPlanLevel("SemiSenior");
        cpl1.setPrevious(cpl2);
        Assert.assertEquals("", cpl2, cpl1.getPrevious());
        Assert.assertEquals("", cpl1, cpl2.getNext());
    }

    @Test
    public void testNextIsReciproc() {
        CareerPlanLevel cpl1 = new CareerPlanLevel("Junior");
        CareerPlanLevel cpl2 = new CareerPlanLevel("SemiSenior");
        cpl1.setNext(cpl2);
        Assert.assertEquals("", cpl2, cpl1.getNext());
        Assert.assertEquals("", cpl1, cpl2.getPrevious());
    }
}
