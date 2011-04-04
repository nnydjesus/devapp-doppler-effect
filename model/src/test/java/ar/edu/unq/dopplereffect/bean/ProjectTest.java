package ar.edu.unq.dopplereffect.bean;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ProjectTest {

    private Project project;

    private int trestMeses = 160 * 3;

    private int cincoDias = 5 * 8;

    @Before
    public void setUp() {
        project = new Project("p1");
        project.setConsideredTime(3, 5);
    }

    @Test
    public void setConsideratedTime() {
        Assert.assertEquals(trestMeses + cincoDias, project.getConsideredTime());
    }
}
