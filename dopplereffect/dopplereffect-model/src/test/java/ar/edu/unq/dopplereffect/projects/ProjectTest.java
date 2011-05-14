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

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.employees.EmployeeBuilder;
import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.helpers.DateHelpers;
import ar.edu.unq.dopplereffect.project.Project;
import ar.edu.unq.dopplereffect.project.ProjectBuilder;
import ar.edu.unq.dopplereffect.project.ProjectHelper;
import ar.edu.unq.dopplereffect.project.Skill;
import ar.edu.unq.dopplereffect.project.SkillLevel;
import ar.edu.unq.dopplereffect.project.SkillType;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public class ProjectTest {

    private static final int YEAR = 2011;

    private static final int MONTH_BASE = 4;

    private static final int DAY_BASE = 4;

    private static Set<Skill> skills = new HashSet<Skill>();
    static {
        skills.addAll(Arrays.asList(createSkill("Hibernate", SkillLevel.EXPERT), createSkill("POO", SkillLevel.EXPERT),
                createSkill("Wicket", SkillLevel.MEDIUM)));
    }

    @Test
    public void setConsideratedTime() {
        final Period consideredEfforr = Period.months(2).plusDays(3);
        final Project project = this.getBuilderWithMoreEffort().withConsideredEffor(consideredEfforr).build();
        Assert.assertEquals(consideredEfforr, project.getTimeProyect());
    }

    @Test
    public void manualAssignmentOk() {
        final Period period = Period.months(2).plusDays(3);
        final Project project = this.getBuilderWithMoreEffort().withConsideredEffor(period).build();
        final IntervalDurationStrategy interval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(YEAR,
                MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR, MONTH_BASE + 2, DAY_BASE)));
        final Employee employee = new EmployeeBuilder().build();
        project.manualAssignment(employee, interval);

        Assert.assertTrue("", project.isAssigned(employee));

    }

    @Test(expected = UserException.class)
    public void manualAssignmentSpendTimeOfProyect() {
        final Period period = Period.months(2).plusDays(1);
        final Project project = this.getBuilderWithMoreEffort().withConsideredEffor(period).build();
        final IntervalDurationStrategy interval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(YEAR,
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
        final IntervalDurationStrategy firstInterval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(
                YEAR, MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR, MONTH_BASE + 1, DAY_BASE)));
        final IntervalDurationStrategy secondInterval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(
                YEAR, MONTH_BASE + 1, DAY_BASE + 1), DateHelpers.getDate(YEAR, MONTH_BASE + 2, DAY_BASE)));

        Employee employee1 = new EmployeeBuilder().build();
        project.manualAssignment(employee1, firstInterval);
        project.manualAssignment(employee1, secondInterval);

        Assert.assertTrue(project.isAssignedInInterval(employee1, firstInterval));
        Assert.assertTrue(project.isAssignedInInterval(employee1, secondInterval));
    }

    @Test(expected = UserException.class)
    public void manualAssignmentOverlaps() {
        final Period period = Period.months(4);
        final Project project = this.getBuilderWithMoreEffort().withConsideredEffor(period).build();
        final IntervalDurationStrategy firstInterval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(
                YEAR, MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR, MONTH_BASE + 1, DAY_BASE)));
        final IntervalDurationStrategy secondInterval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(
                YEAR, MONTH_BASE, 25), DateHelpers.getDate(YEAR, MONTH_BASE + 1, 10)));

        project.manualAssignment(new EmployeeBuilder().build(), firstInterval);
        project.manualAssignment(new EmployeeBuilder().build(), secondInterval);
    }

    @Test(expected = UserException.class)
    public void validateEffort() {
        final Period days = Period.days(2);
        final Project project = new ProjectBuilder().withEstimatedEffort(30).withConsideredEffor(days).build();
        final IntervalDurationStrategy interval = new IntervalDurationStrategy(new Interval(DateHelpers.getDate(YEAR,
                MONTH_BASE, DAY_BASE), DateHelpers.getDate(YEAR, MONTH_BASE, DAY_BASE + 1)));
        project.manualAssignment(new EmployeeBuilder().build(), interval);// 16
        project.manualAssignment(new EmployeeBuilder().build(), interval);// 24
        project.manualAssignment(new EmployeeBuilder().build(), interval);// 32BOOM
    }

    @Test
    public void automaticAssignment() {
        final Period period = Period.months(2);
        DateTime datebase = DateHelpers.getDate(YEAR, MONTH_BASE, DAY_BASE);

        final IntervalDurationStrategy intervalTwoMonth = new IntervalDurationStrategy(new Interval(datebase,
                DateHelpers.getDate(YEAR, MONTH_BASE + 2, DAY_BASE)));

        int effortByEmployee = ProjectHelper.daysToHoursEffort(intervalTwoMonth.getAmountOfDays());

        final IntervalDurationStrategy intervalOneMonth = new IntervalDurationStrategy(new Interval(datebase,
                DateHelpers.getDate(YEAR, MONTH_BASE, DAY_BASE).plusDays((intervalTwoMonth.getAmountOfDays() - 1) / 2)));

        // se calcula 4 empleados en los dos meses enteros
        final Project project = new ProjectBuilder().withConsideredEffor(period)
                .withEstimatedEffort(effortByEmployee * 4).withSkills(skills).build();

        final Employee employee1 = mock(Employee.class);
        final Employee employee2 = mock(Employee.class);
        final Employee employee3 = mock(Employee.class);
        final Employee employee4 = mock(Employee.class);
        final Employee employee5 = mock(Employee.class);
        final Employee employee6 = mock(Employee.class);
        final Employee employee7 = mock(Employee.class);

        IntervalDurationStrategy intervalDurationStrategy = new IntervalDurationStrategy(new Interval(datebase,
                datebase.plus(project.getTimeProyect())));

        when(employee1.sastisfaccionLevelOfSkills(skills)).thenReturn(2);
        when(employee1.getAvailableInterval(intervalDurationStrategy)).thenReturn(intervalOneMonth);
        when(employee1.availabilityLevel(intervalDurationStrategy)).thenReturn(2);
        when(employee1.isFreeAtInterval(intervalOneMonth.getInterval())).thenReturn(true);
        when(employee1.toString()).thenReturn("empleado1"); // para testing

        when(employee2.sastisfaccionLevelOfSkills(skills)).thenReturn(1);
        when(employee2.getAvailableInterval(intervalDurationStrategy)).thenReturn(intervalTwoMonth);
        when(employee2.availabilityLevel(intervalDurationStrategy)).thenReturn(2);
        when(employee2.isFreeAtInterval(intervalTwoMonth.getInterval())).thenReturn(true);
        when(employee2.toString()).thenReturn("empleado2");

        when(employee3.sastisfaccionLevelOfSkills(skills)).thenReturn(4);
        when(employee3.getAvailableInterval(intervalDurationStrategy)).thenReturn(intervalTwoMonth);
        when(employee3.availabilityLevel(intervalDurationStrategy)).thenReturn(1);
        when(employee3.isFreeAtInterval(intervalOneMonth.getInterval())).thenReturn(true);
        when(employee3.toString()).thenReturn("empleado3");

        when(employee4.sastisfaccionLevelOfSkills(skills)).thenReturn(2);
        when(employee4.getAvailableInterval(intervalDurationStrategy)).thenReturn(intervalTwoMonth);
        when(employee4.availabilityLevel(intervalDurationStrategy)).thenReturn(2);
        when(employee4.isFreeAtInterval(intervalTwoMonth.getInterval())).thenReturn(true);
        when(employee4.toString()).thenReturn("empleado4");

        when(employee5.sastisfaccionLevelOfSkills(skills)).thenReturn(4);
        when(employee5.getAvailableInterval(intervalDurationStrategy)).thenReturn(intervalOneMonth);
        when(employee5.availabilityLevel(intervalDurationStrategy)).thenReturn(5);
        when(employee5.isFreeAtInterval(intervalOneMonth.getInterval())).thenReturn(true);
        when(employee5.toString()).thenReturn("empleado5");

        when(employee6.sastisfaccionLevelOfSkills(skills)).thenReturn(5);
        when(employee6.getAvailableInterval(intervalDurationStrategy)).thenReturn(intervalOneMonth);
        when(employee6.availabilityLevel(intervalDurationStrategy)).thenReturn(2);
        when(employee6.isFreeAtInterval(intervalOneMonth.getInterval())).thenReturn(true);
        when(employee6.toString()).thenReturn("empleado6");

        when(employee7.sastisfaccionLevelOfSkills(skills)).thenReturn(1);
        when(employee7.getAvailableInterval(intervalDurationStrategy)).thenReturn(intervalTwoMonth);
        when(employee7.availabilityLevel(intervalDurationStrategy)).thenReturn(1);
        when(employee7.isFreeAtInterval(intervalTwoMonth.getInterval())).thenReturn(true);
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

    public static Skill createSkill(final String type, final SkillLevel level) {
        return new Skill(new SkillType(type), level);
    }
}
