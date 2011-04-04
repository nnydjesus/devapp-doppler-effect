package ar.edu.unq.dopplereffect.bean;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ProyectTest {

    private Proyect proyect;

    private int trestMeses = 160 * 3;

    private int cincoDias = 5 * 8;

    @Before
    public void setUp() {
        proyect = new Proyect("p1");
        proyect.setConsideredTime(3, 5);
    }

    @Test
    public void setConsideratedTime() {
        Assert.assertEquals(trestMeses + cincoDias, proyect.getConsideredTime());
    }
}
