package ar.edu.unq.dopplereffect.calendar;

import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_05;
import static org.mockito.Mockito.*;

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
        calendarStrategy = mock(WeekdayStrategy.class);
        employee = mock(Employee.class);
        assignable = mock(Assignable.class);
        employees = new ArrayList<Employee>();
        when(calendarStrategy.cloneStrategy()).thenReturn(calendarStrategy);
        when(calendarStrategy.getDay()).thenReturn(D_2011_04_05);
        when(calendarStrategy.getTotalDays()).thenReturn(1);
        when(employee.getAssignableForDay(D_2011_04_05)).thenReturn(assignable);
    }

    protected List<Employee> getListWithOneElement() {
        employees.add(employee);
        return employees;
    }

    protected Calendar getCalendar() {
        return new Calendar(calendarStrategy);
    }

    @Test
    public void testGetCalendarEmptyList() {
        Assert.assertTrue(this.getCalendar().getCalendar(employees).isEmpty());
    }

    @Test
    public void testGetCalendarOneElement() {
        Matrix<Employee, DateTime, Assignable> calendar = this.getCalendar().getCalendar(this.getListWithOneElement());
        Assert.assertTrue(calendar.conteinsX(employee));
        Assert.assertTrue(calendar.conteinsXY(employee, D_2011_04_05));
        Assert.assertEquals(assignable, calendar.getXY(employee, D_2011_04_05));

    }
}
