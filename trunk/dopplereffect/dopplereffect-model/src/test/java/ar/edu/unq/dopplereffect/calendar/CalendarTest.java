package ar.edu.unq.dopplereffect.calendar;

import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_05;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.dopplereffect.bean.Assignable;
import ar.edu.unq.dopplereffect.employees.Employee;

/**
 */
public class CalendarTest {

    private CalendarStrategy calendarStrategy;

    private List<Employee> employees;

    private Employee employee;

    private Assignable assignable;

    @Before
    public void startUp() {
        this.setCalendarStrategy(mock(WeekdayStrategy.class));
        this.setEmployee(mock(Employee.class));
        this.setAssignable(mock(Assignable.class));
        this.setEmployees(new ArrayList<Employee>());
        when(this.getCalendarStrategy().cloneStrategy()).thenReturn(this.getCalendarStrategy());
        when(this.getCalendarStrategy().getDay()).thenReturn(D_2011_04_05);
        when(this.getCalendarStrategy().getTotalDays()).thenReturn(1);
        when(this.getEmployee().getAssignableForDay(D_2011_04_05)).thenReturn(this.getAssignable());
    }

    protected List<Employee> getListWithOneElement() {
        this.getEmployees().add(this.getEmployee());
        return this.getEmployees();
    }

    protected Calendar getCalendar() {
        return new Calendar(this.getCalendarStrategy());
    }

    @Test
    public void testGetCalendarEmptyList() {
        Assert.assertTrue(this.getCalendar().getCalendar(this.getEmployees()).isEmpty());
    }

    @Test
    public void testGetCalendarOneElement() {
        Matrix<Employee, DateTime, Assignable> calendar = this.getCalendar().getCalendar(this.getListWithOneElement());
        Assert.assertTrue(calendar.conteinsX(this.getEmployee()));
        Assert.assertTrue(calendar.conteinsXY(this.getEmployee(), D_2011_04_05));
        Assert.assertEquals(this.getAssignable(), calendar.getXY(this.getEmployee(), D_2011_04_05));

    }

    protected void setAssignable(final Assignable assignable) {
        this.assignable = assignable;
    }

    protected Assignable getAssignable() {
        return assignable;
    }

    protected void setEmployee(final Employee employee) {
        this.employee = employee;
    }

    protected Employee getEmployee() {
        return employee;
    }

    protected void setEmployees(final List<Employee> employees) {
        this.employees = employees;
    }

    protected List<Employee> getEmployees() {
        return employees;
    }

    protected void setCalendarStrategy(final CalendarStrategy calendarStrategy) {
        this.calendarStrategy = calendarStrategy;
    }

    protected CalendarStrategy getCalendarStrategy() {
        return calendarStrategy;
    }
}
