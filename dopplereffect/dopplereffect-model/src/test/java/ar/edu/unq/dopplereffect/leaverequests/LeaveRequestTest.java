package ar.edu.unq.dopplereffect.leaverequests;

import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_05;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_06;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_08;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_11;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_13;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.Interval;
import org.junit.Test;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.time.DurationStrategy;

public class LeaveRequestTest {

    @Test
    public void testIncludesDay() { // NOPMD
        DurationStrategy strategy = mock(DurationStrategy.class);
        LeaveRequest request = new LeaveRequestBuilder().withDurationStrategy(strategy).build();
        request.includesDay(D_2011_04_13);
        verify(strategy).includesDay(D_2011_04_13);
    }

    @Test
    public void testAmountOfDays() { // NOPMD
        DurationStrategy strategy = mock(DurationStrategy.class);
        LeaveRequest request = new LeaveRequestBuilder().withDurationStrategy(strategy).build();
        request.getAmountOfDays();
        verify(strategy).getAmountOfDays();
    }

    @Test
    public void testOverlapsAssignment() { // NOPMD
        Interval interval = new Interval(D_2011_04_05, D_2011_04_06);
        DurationStrategy strategy = mock(DurationStrategy.class);
        LeaveRequest request = new LeaveRequestBuilder().withDurationStrategy(strategy).build();
        request.overlapsAssignment(interval);
        verify(strategy).overlapsInterval(interval);
    }

    @Test
    public void testOverlapsWithLeaveRequestIntersectingInTheEnd() { // NOPMD
        DurationStrategy strategy = mock(DurationStrategy.class);
        LeaveRequest leaveReq1 = new LeaveRequestBuilder().withDurationStrategy(strategy).build();
        LeaveRequest leaveReq2 = new LeaveRequestBuilder().build();
        leaveReq1.overlapsWith(leaveReq2);
        verify(strategy).overlapsWith(leaveReq2);
    }

    @Test
    public void testValidateEmployeeWhenRequestingLessDaysThanSpecified() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            .withType(new LeaveRequestTypeBuilder().withMinLimit(7).build())
            .withInterval(D_2011_04_05, D_2011_04_08)
            .build();
        // @formatter:on
        assertFalse("Licencia de 4 dias no debe ser valida (minimo 7)", request.isValidFor(mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenRequestingMoreDaysThanSpecified() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            .withType(new LeaveRequestTypeBuilder().withMaxLimit(7).build())
            .withInterval(D_2011_04_05, D_2011_04_13)
            .build();
        // @formatter:on
        assertFalse("Licencia de 9 dias no debe ser valida (maximo 7)", request.isValidFor(mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenRequestingTheMinimumPossibleInterval() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            .withType(new LeaveRequestTypeBuilder().withMinLimit(2).build())
            .withInterval(D_2011_04_05, D_2011_04_06)      // 2 dias de licencia
            .build();
        // @formatter:on
        assertTrue("La validacion de la licencia fallo (deberia validarla correctamente)",
                request.isValidFor(mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenRequestingTheMaximumPossibleInterval() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            .withType(new LeaveRequestTypeBuilder().withMaxLimit(7).build())
            .withInterval(D_2011_04_05, D_2011_04_11)      // 7 dias de licencia
            .build();
        // @formatter:on
        assertTrue("La validacion de la licencia fallo (deberia validarla correctamente)",
                request.isValidFor(mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenRequestingRightAmountOfDays() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            .withType(new LeaveRequestTypeBuilder().withMinLimit(2).withMaxLimit(7).build())
            .withInterval(D_2011_04_05, D_2011_04_08)      // 4 dias de licencia
            .build();
        // @formatter:on
        assertTrue("La validacion de la licencia fallo (deberia validarla correctamente)",
                request.isValidFor(mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenAlreadyRequestedAllPossibleDays() {
        int maxDays = 15;
        Employee empl = mock(Employee.class);
        LeaveRequestCustomType leaveReqType = new LeaveRequestTypeBuilder().withMaxDaysInYear(maxDays).build();
        LeaveRequest request = new LeaveRequestBuilder().withType(leaveReqType)
                .withInterval(D_2011_04_05, D_2011_04_08).build();
        when(empl.daysRequestedInYear(leaveReqType, 2011)).thenReturn(maxDays);
        assertFalse("la validacion de la licencia fallo", request.isValidFor(empl));
    }

    @Test
    public void testValidateEmployeeWhenAlreadyRequestedForTheSameDays() {
        Employee empl = mock(Employee.class);
        LeaveRequest request1 = new LeaveRequestBuilder().withType(new LeaveRequestTypeBuilder().build())
                .withInterval(D_2011_04_05, D_2011_04_08).build();
        LeaveRequest request2 = new LeaveRequestBuilder().withType(new LeaveRequestTypeBuilder().build())
                .withInterval(D_2011_04_06, D_2011_04_11).build();
        Set<LeaveRequest> reqs = new HashSet<LeaveRequest>();
        reqs.add(request1);
        when(empl.getLeaveRequests()).thenReturn(reqs);
        assertFalse("la validacion de la licencia fallo", request2.isValidFor(empl));
        verify(empl).getLeaveRequests();
    }

    @Test
    public void testIsLeaveRequest() {
        LeaveRequest anyLeaveRequest = new LeaveRequestBuilder().build();
        assertTrue("una licencia ES una licencia", anyLeaveRequest.isLeaveRequest());
    }
}