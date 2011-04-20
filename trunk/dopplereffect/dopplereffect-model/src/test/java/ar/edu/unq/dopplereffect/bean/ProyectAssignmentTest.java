package ar.edu.unq.dopplereffect.bean;

import static org.mockito.Mockito.*;
import junit.framework.Assert;

import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public class ProyectAssignmentTest {

    private Employee employee1;

    private Employee employee2;

    private IntervalDurationStrategy interval1;

    private IntervalDurationStrategy interval2;

    @Before
    public void setUp() {
        employee1 = mock(Employee.class);
        employee2 = mock(Employee.class);
        interval1 = mock(IntervalDurationStrategy.class);
        interval2 = mock(IntervalDurationStrategy.class);
        Interval interval = new Interval(0, 5);

        when(interval1.overlapsInterval(interval)).thenReturn(true);
        when(interval1.getInterval()).thenReturn(interval);
    }

    protected ProjectAssignment createProyect() {
        ProjectAssignment projectAssignment = new ProjectAssignment(employee1);
        projectAssignment.addInterval(interval1);
        return projectAssignment;
    }

    @Test
    public void testIsAssignedTrue() {
        Assert.assertTrue("Fallo en la asignacion del empleado ", this.createProyect().isAssigned(employee1));
    }

    @Test
    public void testContainsIntervalTrue() {
        Assert.assertTrue("La asignacion contiene el intervalo ", this.createProyect().containsInterval(interval1));
    }

    @Test
    public void testOverlapsAssignmentTrue() {
        Assert.assertTrue("Fallo, la asignacion se superpone con el intervalo, sin embargo el test dice que no", this
                .createProyect().overlapsAssignment(interval1));
    }

    @Test
    public void testIsAssignedFalse() {
        Assert.assertFalse("Fallo en la asignacion del empleado", this.createProyect().isAssigned(employee2));
    }

    @Test
    public void testContainsIntervalFalse() {
        Assert.assertFalse("La asignacion no contiene el intervalo", this.createProyect().containsInterval(interval2));
    }

    @Test
    public void testOverlapsAssignmentFasle() {
        Assert.assertFalse("Fallo, la asignacion no se superpone con el intervalo, sin embargo el test dice que si",
                this.createProyect().overlapsAssignment(interval2));
    }
}
