package ar.edu.unq.dopplereffect.bean;

import junit.framework.Assert;

import org.joda.time.Interval;
import org.joda.time.Period;
import org.junit.Test;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.employees.EmployeeBuilder;
import ar.edu.unq.dopplereffect.exception.UserException;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public class ProjectTest {

    private static final int YEAR = 2011;

    private static final int MONTH_BASE = 4;

    private static final int DAY_BASE = 4;

    @Test
    public void setConsideratedTime() {
        final Period consideredEfforr = Period.months(2).plusDays(3);
        final Project project = new ProjectBuilder().withConsideredEffor(consideredEfforr).build();
        Assert.assertEquals(consideredEfforr, project.getConsideredEffor());
    }

    @Test
    public void manualAssignmentOk() {
        final Period period = Period.months(2).plusDays(3);
        final Project project = new ProjectBuilder().withConsideredEffor(period).build();
        final IntervalDurationStrategy interval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(YEAR,
                MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR, MONTH_BASE + 2, DAY_BASE)));
        final Employee employee = new EmployeeBuilder().build();
        project.manualAssignment(employee, interval);

        Assert.assertTrue("", project.isAssigned(employee));

    }

    @Test(expected = UserException.class)
    public void manualAssignmentSpendTimeOfProyect() {
        final Period period = Period.months(2).plusDays(1);
        final Project project = new ProjectBuilder().withConsideredEffor(period).build();
        final IntervalDurationStrategy interval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(YEAR,
                MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR, MONTH_BASE + 2, DAY_BASE + 2)));

        project.manualAssignment(new EmployeeBuilder().build(), interval);
    }

    @Test
    public void manualAssignmentTwoAssignment() {
        Period period = Period.months(4);
        Project project = new ProjectBuilder().withConsideredEffor(period).build();
        final IntervalDurationStrategy firstInterval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(
                YEAR, MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR, MONTH_BASE + 1, DAY_BASE)));
        final IntervalDurationStrategy secondInterval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(
                YEAR, MONTH_BASE + 1, DAY_BASE + 1), DateHelpers.getDate(YEAR, MONTH_BASE + 2, DAY_BASE)));

        Employee employee1 = new EmployeeBuilder().build();
        project.manualAssignment(employee1, firstInterval);
        project.manualAssignment(employee1, secondInterval);

        Assert.assertTrue(project.isAssignedInInverval(employee1, firstInterval));
        Assert.assertTrue(project.isAssignedInInverval(employee1, secondInterval));
    }

    @Test(expected = UserException.class)
    public void manualAssignmentOverlaps() {
        final Period period = Period.months(4);
        final Project project = new ProjectBuilder().withConsideredEffor(period).build();
        final IntervalDurationStrategy firstInterval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(
                YEAR, MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR, MONTH_BASE + 1, DAY_BASE)));
        final IntervalDurationStrategy secondInterval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(
                YEAR, MONTH_BASE, 25), DateHelpers.getDate(YEAR, MONTH_BASE + 1, 10)));

        project.manualAssignment(new EmployeeBuilder().build(), firstInterval);
        project.manualAssignment(new EmployeeBuilder().build(), secondInterval);
    }
}
