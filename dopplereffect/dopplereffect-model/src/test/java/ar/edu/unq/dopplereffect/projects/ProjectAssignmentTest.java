package ar.edu.unq.dopplereffect.projects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.project.ProjectAssignment;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public class ProjectAssignmentTest {

    private Employee employee1;

    private Employee employee2;

    private IntervalDurationStrategy interval1;

    private IntervalDurationStrategy interval2;

    @Before
    public void setUp() {
        this.setEmployee1(mock(Employee.class));
        this.setEmployee2(mock(Employee.class));
        this.setInterval1(mock(IntervalDurationStrategy.class));
        this.setInterval2(mock(IntervalDurationStrategy.class));
        Interval interval = new Interval(0, 5);

        when(this.getInterval1().overlapsInterval(interval)).thenReturn(true);
        when(this.getInterval1().getInterval()).thenReturn(interval);
    }

    protected ProjectAssignment createProyect() {
        ProjectAssignment projectAssignment = new ProjectAssignment(this.getEmployee1());
        projectAssignment.addInterval(this.getInterval1());
        return projectAssignment;
    }

    @Test
    public void testIsAssignedTrue() {
        Assert.assertTrue("Fallo en la asignacion del empleado ", this.createProyect().isAssigned(this.getEmployee1()));
    }

    @Test
    public void testContainsIntervalTrue() {
        Assert.assertTrue("La asignacion contiene el intervalo ",
                this.createProyect().containsInterval(this.getInterval1()));
    }

    @Test
    public void testOverlapsAssignmentTrue() {
        Assert.assertTrue("Fallo, la asignacion se superpone con el intervalo, sin embargo el test dice que no", this
                .createProyect().overlapsAssignment(this.getInterval1()));
    }

    @Test
    public void testIsAssignedFalse() {
        Assert.assertFalse("Fallo en la asignacion del empleado", this.createProyect().isAssigned(this.getEmployee2()));
    }

    @Test
    public void testContainsIntervalFalse() {
        Assert.assertFalse("La asignacion no contiene el intervalo",
                this.createProyect().containsInterval(this.getInterval2()));
    }

    @Test
    public void testOverlapsAssignmentFasle() {
        Assert.assertFalse("Fallo, la asignacion no se superpone con el intervalo, sin embargo el test dice que si",
                this.createProyect().overlapsAssignment(this.getInterval2()));
    }

    protected void setInterval2(final IntervalDurationStrategy interval2) {
        this.interval2 = interval2;
    }

    protected IntervalDurationStrategy getInterval2() {
        return interval2;
    }

    protected void setInterval1(final IntervalDurationStrategy interval1) {
        this.interval1 = interval1;
    }

    protected IntervalDurationStrategy getInterval1() {
        return interval1;
    }

    protected void setEmployee2(final Employee employee2) {
        this.employee2 = employee2;
    }

    protected Employee getEmployee2() {
        return employee2;
    }

    protected void setEmployee1(final Employee employee1) {
        this.employee1 = employee1;
    }

    protected Employee getEmployee1() {
        return employee1;
    }
}
