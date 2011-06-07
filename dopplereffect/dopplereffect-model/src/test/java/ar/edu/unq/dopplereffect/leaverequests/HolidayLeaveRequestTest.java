package ar.edu.unq.dopplereffect.leaverequests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.employees.EmployeeTimeCalculator;

public class HolidayLeaveRequestTest {

    private LeaveRequestCustomType createHolidayLeaveRequestType(final int minLimit, final int maxLimit) {
        return new LeaveRequestCustomType("Holiday", 0, minLimit, maxLimit);
    }

    @Test
    public void testIsntValidForEmployeeRequestMoreDaysThanPermitted() {
        // GIVEN
        LeaveRequestCustomType holidayType = this.createHolidayLeaveRequestType(7, 15);
        holidayType.initialConfig(7);
        holidayType.configure(1, 7);
        Employee employee = mock(Employee.class);
        LeaveRequest leaveReq = mock(LeaveRequest.class);
        EmployeeTimeCalculator calculator = mock(EmployeeTimeCalculator.class);
        // WHEN
        when(employee.getSeniority()).thenReturn(1);
        when(calculator.daysRequestedInYear(employee, holidayType, 2011)).thenReturn(2);
        when(leaveReq.getAmountOfDays()).thenReturn(8);
        when(leaveReq.getYear()).thenReturn(2011);
        // THEN
        assertFalse("la validacion de las vacaciones fallo", holidayType.isValidFor(leaveReq, employee));
    }

    @Test
    public void testIsValidForEmployeeRequestRightDays() {
        // GIVEN
        LeaveRequestCustomType holidayType = this.createHolidayLeaveRequestType(7, 15);
        Employee employee = mock(Employee.class);
        LeaveRequest leaveReq = mock(LeaveRequest.class);
        EmployeeTimeCalculator calculator = mock(EmployeeTimeCalculator.class);
        // WHEN
        holidayType.initialConfig(7);
        holidayType.configure(1, 7);
        when(employee.getSeniority()).thenReturn(1);
        when(calculator.daysRequestedInYear(employee, holidayType, 2011)).thenReturn(0);
        when(leaveReq.getAmountOfDays()).thenReturn(7);
        when(leaveReq.getYear()).thenReturn(2011);
        // THEN
        assertTrue("la validacion de las vacaciones fallo", holidayType.isValidFor(leaveReq, employee));
    }

    @Test
    public void testGetCorrespondingDays() {
        // GIVEN
        LeaveRequestCustomType holidayType = this.createHolidayLeaveRequestType(7, 15);
        // WHEN
        holidayType.configure(0, 7);
        holidayType.configure(1, 15);
        holidayType.configure(5, 21);
        // THEN
        assertEquals("la configuracion no retorno 7", 7, holidayType.getCorrespondingDays(0));
        assertEquals("la configuracion no retorno 15", 15, holidayType.getCorrespondingDays(1));
        assertEquals("la configuracion no retorno 15", 15, holidayType.getCorrespondingDays(2));
        assertEquals("la configuracion no retorno 15", 15, holidayType.getCorrespondingDays(3));
        assertEquals("la configuracion no retorno 15", 15, holidayType.getCorrespondingDays(4));
        assertEquals("la configuracion no retorno 21", 21, holidayType.getCorrespondingDays(5));
        assertEquals("la configuracion no retorno 21", 21, holidayType.getCorrespondingDays(6));
    }
}