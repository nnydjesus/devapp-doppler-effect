package ar.edu.unq.dopplereffect.projects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.junit.Test;

import ar.edu.unq.dopplereffect.builders.employees.EmployeeBuilder;
import ar.edu.unq.dopplereffect.builders.projects.ProjectBuilder;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.employees.EmployeeTimeCalculator;
import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.helpers.DateHelpers;
import ar.edu.unq.dopplereffect.helpers.SkillHelpers;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public class ProjectTest {

    private static final int YEAR = 2011;

    private static final int MONTH_BASE = 4;

    private static final int DAY_BASE = 4;

    private static Set<Skill> skills = new HashSet<Skill>();
    static {
        // @formatter:off
        skills.addAll(Arrays.asList(
                SkillHelpers.HIBERNATE_EXPERT, 
                SkillHelpers.WICKET_EXPERT,
                SkillHelpers.MYSQL_MEDIUM)
        );
        // @formatter:on
    }

    @Test
    public void setConsideratedTime() {
        final Period consideredEfforr = Period.months(2).plusDays(3);
        final Project project = this.getBuilderWithMoreEffort().withConsideredEffor(consideredEfforr).build();
        Assert.assertEquals(consideredEfforr, project.getTimeProyect());
    }

    @Test
    public void manualAssignmentOk() {
        Period period = Period.months(2).plusDays(3);
        Project project = this.getBuilderWithMoreEffort().withConsideredEffor(period).build();
        IntervalDurationStrategy interval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(YEAR,
                MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR, MONTH_BASE + 2, DAY_BASE)));
        Employee employee = new EmployeeBuilder().build();
        project.manualAssignment(employee, interval);

        Assert.assertTrue("", project.isAssigned(employee));

    }

    @Test(expected = UserException.class)
    public void manualAssignmentSpendTimeOfProyect() {
        Period period = Period.months(2).plusDays(1);
        Project project = this.getBuilderWithMoreEffort().withConsideredEffor(period).build();
        IntervalDurationStrategy interval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(YEAR,
                MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR, MONTH_BASE + 2, DAY_BASE + 2)));

        project.manualAssignment(new EmployeeBuilder().build(), interval);
    }

    private ProjectBuilder getBuilderWithMoreEffort() {
        return new ProjectBuilder().withEstimatedEffort(1000000);
    }

    @Test
    public void manualAssignmentTwoAssignment() {
        Period period = Period.months(4);
        Project project = this.getBuilderWithMoreEffort().withConsideredEffor(period).build();
        IntervalDurationStrategy firstInterval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(YEAR,
                MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR, MONTH_BASE + 1, DAY_BASE)));
        IntervalDurationStrategy secondInterval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(YEAR,
                MONTH_BASE + 1, DAY_BASE + 1), DateHelpers.getDate(YEAR, MONTH_BASE + 2, DAY_BASE)));

        Employee employee1 = new EmployeeBuilder().build();
        project.manualAssignment(employee1, firstInterval);
        project.manualAssignment(employee1, secondInterval);

        Assert.assertTrue(project.isAssignedInInterval(employee1, firstInterval));
        Assert.assertTrue(project.isAssignedInInterval(employee1, secondInterval));
    }

    @Test(expected = UserException.class)
    public void manualAssignmentOverlaps() {
        Period period = Period.months(4);
        Project project = this.getBuilderWithMoreEffort().withConsideredEffor(period).build();
        IntervalDurationStrategy firstInterval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(YEAR,
                MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR, MONTH_BASE + 1, DAY_BASE)));
        IntervalDurationStrategy secondInterval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(YEAR,
                MONTH_BASE, 25), DateHelpers.getDate(YEAR, MONTH_BASE + 1, 10)));

        project.manualAssignment(new EmployeeBuilder().build(), firstInterval);
        project.manualAssignment(new EmployeeBuilder().build(), secondInterval);
    }

    @Test(expected = UserException.class)
    public void validateEffort() {
        Period days = Period.days(2);
        Project project = new ProjectBuilder().withEstimatedEffort(30).withConsideredEffor(days).build();
        IntervalDurationStrategy interval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(YEAR,
                MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR, MONTH_BASE, DAY_BASE + 1)));
        project.manualAssignment(new EmployeeBuilder().build(), interval);// 16
        project.manualAssignment(new EmployeeBuilder().build(), interval);// 24
        project.manualAssignment(new EmployeeBuilder().build(), interval);// 32BOOM
    }

    @Test
    public void automaticAssignment() {
        Period period = Period.months(2);
        DateTime datebase = DateHelpers.getDate(YEAR, MONTH_BASE, DAY_BASE);

        IntervalDurationStrategy intervalTwoMonth = new IntervalDurationStrategy(new Interval(datebase,
                DateHelpers.getDate(YEAR, MONTH_BASE + 2, DAY_BASE)));

        long effortByEmployee = ProjectHelper.daysToHoursEffort(intervalTwoMonth.getAmountOfDays());

        IntervalDurationStrategy intervalOneMonth = new IntervalDurationStrategy(new Interval(datebase, DateHelpers
                .getDate(YEAR, MONTH_BASE, DAY_BASE).plusDays((intervalTwoMonth.getAmountOfDays() - 1) / 2)));

        EmployeeTimeCalculator calculator = mock(EmployeeTimeCalculator.class);

        // se calcula 4 empleados en los dos meses enteros
        Project project = new ProjectBuilder().withConsideredEffor(period).withEstimatedEffort(effortByEmployee * 4)
                .withSkills(skills).withEmployeeTimeCalculator(calculator).build();

        Employee employee1 = mock(Employee.class);
        Employee employee2 = mock(Employee.class);
        Employee employee3 = mock(Employee.class);
        Employee employee4 = mock(Employee.class);
        Employee employee5 = mock(Employee.class);
        Employee employee6 = mock(Employee.class);
        Employee employee7 = mock(Employee.class);

        IntervalDurationStrategy intervalDurationStrategy = new IntervalDurationStrategy(new Interval(datebase,
                datebase.plus(project.getTimeProyect())));

        when(employee1.satisfactionLevelOfSkills(skills)).thenReturn(2);
        when(calculator.getAvailableIntervals(employee1, intervalDurationStrategy)).thenReturn(
                Arrays.asList(intervalOneMonth));
        when(calculator.availabilityLevel(employee1, intervalDurationStrategy)).thenReturn(2);
        when(calculator.isFreeAtInterval(employee1, intervalOneMonth.getInterval())).thenReturn(true);
        when(employee1.toString()).thenReturn("empleado1"); // para testing

        when(employee2.satisfactionLevelOfSkills(skills)).thenReturn(1);
        when(calculator.getAvailableIntervals(employee2, intervalDurationStrategy)).thenReturn(
                Arrays.asList(intervalTwoMonth));
        when(calculator.availabilityLevel(employee2, intervalDurationStrategy)).thenReturn(2);
        when(calculator.isFreeAtInterval(employee2, intervalTwoMonth.getInterval())).thenReturn(true);
        when(employee2.toString()).thenReturn("empleado2");

        when(employee3.satisfactionLevelOfSkills(skills)).thenReturn(4);
        when(calculator.getAvailableIntervals(employee3, intervalDurationStrategy)).thenReturn(
                Arrays.asList(intervalTwoMonth));
        when(calculator.availabilityLevel(employee3, intervalDurationStrategy)).thenReturn(1);
        when(calculator.isFreeAtInterval(employee3, intervalOneMonth.getInterval())).thenReturn(true);
        when(employee3.toString()).thenReturn("empleado3");

        when(employee4.satisfactionLevelOfSkills(skills)).thenReturn(2);
        when(calculator.getAvailableIntervals(employee4, intervalDurationStrategy)).thenReturn(
                Arrays.asList(intervalTwoMonth));
        when(calculator.availabilityLevel(employee4, intervalDurationStrategy)).thenReturn(2);
        when(calculator.isFreeAtInterval(employee4, intervalTwoMonth.getInterval())).thenReturn(true);
        when(employee4.toString()).thenReturn("empleado4");

        when(employee5.satisfactionLevelOfSkills(skills)).thenReturn(4);
        when(calculator.getAvailableIntervals(employee5, intervalDurationStrategy)).thenReturn(
                Arrays.asList(intervalOneMonth));
        when(calculator.availabilityLevel(employee5, intervalDurationStrategy)).thenReturn(5);
        when(calculator.isFreeAtInterval(employee5, intervalOneMonth.getInterval())).thenReturn(true);
        when(employee5.toString()).thenReturn("empleado5");

        when(employee6.satisfactionLevelOfSkills(skills)).thenReturn(5);
        when(calculator.getAvailableIntervals(employee6, intervalDurationStrategy)).thenReturn(
                Arrays.asList(intervalOneMonth));
        when(calculator.availabilityLevel(employee6, intervalDurationStrategy)).thenReturn(2);
        when(calculator.isFreeAtInterval(employee6, intervalOneMonth.getInterval())).thenReturn(true);
        when(employee6.toString()).thenReturn("empleado6");

        when(employee7.satisfactionLevelOfSkills(skills)).thenReturn(1);
        when(calculator.getAvailableIntervals(employee7, intervalDurationStrategy)).thenReturn(
                Arrays.asList(intervalTwoMonth));
        when(calculator.availabilityLevel(employee7, intervalDurationStrategy)).thenReturn(1);
        when(calculator.isFreeAtInterval(employee7, intervalTwoMonth.getInterval())).thenReturn(true);
        when(employee7.toString()).thenReturn("empleado7");

        List<Employee> employees = Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6,
                employee7);
        List<Employee> employeesChosen = Arrays.asList(employee7, employee2, employee4, employee1, employee3);

        project.automaticAssignment(employees, datebase);

        Assert.assertEquals(5, project.getAssignedEmployees().size());

        for (Employee employee : employeesChosen) {
            Assert.assertTrue(project.isAssigned(employee));
        }

    }
}
