package ar.edu.unq.dopplereffect.bean;

import junit.framework.Assert;

import org.joda.time.Interval;
import org.joda.time.Period;
import org.junit.Test;

import ar.edu.unq.dopplereffect.exception.UserException;

public class ProjectTest {

    private static final int YEAR = 2011;

    private static final int MONTH_BASE = 4;

    private static final int DAY_BASE = 4;

    @Test
    public void setConsideratedTime() {
        Period consideredEfforr = new PeriodBuilder().withMonth(2).withDays(3).build();
        Project project = new ProjectBuilder().withConsideredEffor(consideredEfforr).build();
        Assert.assertEquals(consideredEfforr, project.getConsideredEffor());
    }

    @Test
    public void manualAssignmentOk() {
        Period period = new PeriodBuilder().withMonth(2).withDays(3).build();
        Project project = new ProjectBuilder().withConsideredEffor(period).build();
        Interval interval = new Interval(DateHelpers.getDate(YEAR, MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR,
                MONTH_BASE + 2, DAY_BASE));
        Employee employee = new EmployeeBuilder().build();
        project.manualAssignment(employee, interval);

        Assert.assertTrue("", project.isAssigned(employee));

    }

    @Test(expected = UserException.class)
    public void manualAssignmentSpendTimeOfProyect() {
        Period period = new PeriodBuilder().withMonth(2).withDays(1).build();
        Project project = new ProjectBuilder().withConsideredEffor(period).build();
        Interval interval = new Interval(DateHelpers.getDate(YEAR, MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR,
                MONTH_BASE + 2, DAY_BASE + 2));

        project.manualAssignment(new EmployeeBuilder().build(), interval);
    }

    @Test
    public void manualAssignmentTwoAssignment() {
        Period period = new PeriodBuilder().withMonth(4).build();
        Project project = new ProjectBuilder().withConsideredEffor(period).build();
        Interval firstInterval = new Interval(DateHelpers.getDate(YEAR, MONTH_BASE, DAY_BASE), DateHelpers.getDate(
                YEAR, MONTH_BASE + 1, DAY_BASE));
        Interval secondInterval = new Interval(DateHelpers.getDate(YEAR, MONTH_BASE + 1, DAY_BASE),
                DateHelpers.getDate(YEAR, MONTH_BASE + 2, DAY_BASE));

        project.manualAssignment(new EmployeeBuilder().build(), firstInterval);
        project.manualAssignment(new EmployeeBuilder().build(), secondInterval);
    }

    @Test(expected = UserException.class)
    public void manualAssignmentOverlaps() {
        Period period = new PeriodBuilder().withMonth(4).build();
        Project project = new ProjectBuilder().withConsideredEffor(period).build();
        Interval firstInterval = new Interval(DateHelpers.getDate(YEAR, MONTH_BASE, DAY_BASE), DateHelpers.getDate(
                YEAR, MONTH_BASE + 1, DAY_BASE));
        Interval secondInterval = new Interval(DateHelpers.getDate(YEAR, MONTH_BASE, 25), DateHelpers.getDate(YEAR,
                MONTH_BASE + 1, 10));

        project.manualAssignment(new EmployeeBuilder().build(), firstInterval);
        project.manualAssignment(new EmployeeBuilder().build(), secondInterval);
    }
}
