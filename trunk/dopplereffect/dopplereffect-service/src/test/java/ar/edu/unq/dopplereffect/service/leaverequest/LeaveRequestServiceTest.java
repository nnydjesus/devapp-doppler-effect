package ar.edu.unq.dopplereffect.service.leaverequest;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

import java.text.SimpleDateFormat;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.exceptions.ValidationException;
import ar.edu.unq.dopplereffect.service.util.SpringServiceTest;

public class LeaveRequestServiceTest extends SpringServiceTest {

    @Autowired
    private LeaveRequestServiceImpl service;

    @Test
    public void testStartDateLessThanEndDate() throws Exception {
        LeaveRequestDTO leaveRequest = LeaveRequestHelpers.createValidLeaveRequestDTOFor(service);
        leaveRequest.setStartDate(new SimpleDateFormat("yyyy-mm-dd").parse("2011-06-06"));
        leaveRequest.setEndDate(new SimpleDateFormat("yyyy-mm-dd").parse("2011-06-02"));
        this.saveMustFailFor(leaveRequest);
    }

    @Test
    public void testReasonNotNull() throws Exception {
        LeaveRequestDTO leaveRequest = LeaveRequestHelpers.createValidLeaveRequestDTOFor(service);
        leaveRequest.setReason(null);
        this.saveMustFailFor(leaveRequest);
    }

    @Test
    public void testReasonBlank() throws Exception {
        LeaveRequestDTO leaveRequest = LeaveRequestHelpers.createValidLeaveRequestDTOFor(service);
        leaveRequest.setReason("                 ");
        this.saveMustFailFor(leaveRequest);
    }

    @Test
    public void testInexistentReason() throws Exception {
        LeaveRequestDTO leaveRequest = LeaveRequestHelpers.createValidLeaveRequestDTOFor(service);
        leaveRequest.setReason("zaza");
        when(service.getLeaveRequestTypeRepo().searchByReason("zaza")).thenThrow(new UserException());
        this.saveMustFailFor(leaveRequest);
    }

    @Test
    public void testAlreadyHasALeaveRequestInThePeriod() throws Exception {
        Employee empMock = mock(Employee.class);
        LeaveRequestDTO leaveRequest = LeaveRequestHelpers.createValidLeaveRequestDTOFor(service, empMock);
        when(empMock.hasLeaveRequestInDay((DateTime) anyObject())).thenReturn(true);
        this.saveMustFailFor(leaveRequest);
    }

    private void saveMustFailFor(final LeaveRequestDTO leaveRequest) {
        try {
            service.newLeaveRequest(leaveRequest);
            Assert.fail();
        } catch (ValidationException exc) {
            exc.getMessage();
        }
    }
}
