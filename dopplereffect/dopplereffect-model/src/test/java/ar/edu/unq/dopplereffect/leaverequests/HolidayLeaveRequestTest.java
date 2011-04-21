package ar.edu.unq.dopplereffect.leaverequests;

import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_05;
import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_11;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import ar.edu.unq.dopplereffect.employees.Employee;

public class HolidayLeaveRequestTest {

    @Test
    public void testIsntValidForEmployeeRequestMoreDaysThanPermitted() {
        HolidayLeaveRequest leaveReqType = new HolidayLeaveRequest(7, 15);
        leaveReqType.configure(1, 7);
        LeaveRequest leaveReq = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_11)
                .withType(leaveReqType).build();
        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employee.getSeniority()).thenReturn(1);
        Mockito.when(employee.daysRequestedInYear(leaveReqType, 2011)).thenReturn(2);
        Assert.assertFalse("", leaveReqType.isValidFor(leaveReq, employee));
    }

    @Test
    public void testIsValidForEmployeeRequestRightDays() {
        HolidayLeaveRequest leaveReqType = new HolidayLeaveRequest(7, 15);
        leaveReqType.configure(1, 7);
        LeaveRequest leaveReq = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_11)
                .withType(leaveReqType).build();
        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employee.getSeniority()).thenReturn(1);
        Mockito.when(employee.daysRequestedInYear(leaveReqType, 2011)).thenReturn(0);
        Assert.assertTrue("", leaveReqType.isValidFor(leaveReq, employee));
    }

    @Test
    public void testGetCorrespondingDays() {
        HolidayLeaveRequest leaveReqType = new HolidayLeaveRequest(7, 15);
        leaveReqType.configure(0, 7);
        leaveReqType.configure(1, 15);
        leaveReqType.configure(5, 21);
        Assert.assertEquals("", 7, leaveReqType.getCorrespondingDays(0));
        Assert.assertEquals("", 15, leaveReqType.getCorrespondingDays(1));
        Assert.assertEquals("", 15, leaveReqType.getCorrespondingDays(2));
        Assert.assertEquals("", 15, leaveReqType.getCorrespondingDays(3));
        Assert.assertEquals("", 15, leaveReqType.getCorrespondingDays(4));
        Assert.assertEquals("", 21, leaveReqType.getCorrespondingDays(5));
        Assert.assertEquals("", 21, leaveReqType.getCorrespondingDays(6));
    }
}
