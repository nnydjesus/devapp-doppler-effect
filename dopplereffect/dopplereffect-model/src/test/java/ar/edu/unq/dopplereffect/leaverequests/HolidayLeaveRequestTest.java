package ar.edu.unq.dopplereffect.leaverequests;

import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_05;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_11;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import ar.edu.unq.dopplereffect.employees.Employee;

public class HolidayLeaveRequestTest {

    @Test
    public void testIsntValidForEmployeeRequestMoreDaysThanPermitted() {
        // GIVEN
        HolidayLeaveRequest leaveReqType = new HolidayLeaveRequest(7, 15);
        leaveReqType.configure(1, 7);
        LeaveRequest leaveReq = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_11)
                .withType(leaveReqType).build();
        Employee employee = mock(Employee.class);

        when(employee.getSeniority()).thenReturn(1);
        when(employee.daysRequestedInYear(leaveReqType, 2011)).thenReturn(2);
        // THEN
        assertFalse("la validacion de las vacaciones fallo", leaveReqType.isValidFor(leaveReq, employee));
    }

    @Test
    public void testIsValidForEmployeeRequestRightDays() {
        // WHEN
        HolidayLeaveRequest leaveReqType = new HolidayLeaveRequest(7, 15);
        leaveReqType.configure(1, 7);
        LeaveRequest leaveReq = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_11)
                .withType(leaveReqType).build();
        Employee employee = mock(Employee.class);
        when(employee.getSeniority()).thenReturn(1);
        when(employee.daysRequestedInYear(leaveReqType, 2011)).thenReturn(0);
        // THEN (esta es una notación muy comun cuando se hacen test ...
        // GIVEN,WHEN,THEN)
        assertTrue("la validacion de las vacaciones fallo", leaveReqType.isValidFor(leaveReq, employee));
    }

    @Test
    public void testGetCorrespondingDays() {
        HolidayLeaveRequest leaveReqType = new HolidayLeaveRequest(7, 15);
        leaveReqType.configure(0, 7);
        leaveReqType.configure(1, 15);
        leaveReqType.configure(5, 21);
        assertEquals("", 7, leaveReqType.getCorrespondingDays(0));
        assertEquals("", 15, leaveReqType.getCorrespondingDays(1));
        assertEquals("", 15, leaveReqType.getCorrespondingDays(2));
        assertEquals("", 15, leaveReqType.getCorrespondingDays(3));
        assertEquals("", 15, leaveReqType.getCorrespondingDays(4));
        assertEquals("", 21, leaveReqType.getCorrespondingDays(5));
        assertEquals("", 21, leaveReqType.getCorrespondingDays(6));
    }
}
