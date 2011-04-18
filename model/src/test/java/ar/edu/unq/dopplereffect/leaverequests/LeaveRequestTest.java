package ar.edu.unq.dopplereffect.leaverequests;

import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_05;
import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_06;
import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_08;
import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_11;
import static ar.edu.unq.dopplereffect.bean.DateHelpers.D_2011_04_13;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.Interval;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import ar.edu.unq.dopplereffect.employees.Employee;

public class LeaveRequestTest {

    @Test
    @SuppressWarnings("PMD")
    public void testIncludesDay() {
        LeaveRequestDurationStrategy strategy = Mockito.mock(LeaveRequestDurationStrategy.class);
        LeaveRequest request = new LeaveRequestBuilder().withDurationStrategy(strategy).build();
        request.includesDay(D_2011_04_13);
        Mockito.verify(strategy).includesDay(D_2011_04_13);
    }

    @Test
    @SuppressWarnings("PMD")
    public void testAmountOfDays() {
        LeaveRequestDurationStrategy strategy = Mockito.mock(LeaveRequestDurationStrategy.class);
        LeaveRequest request = new LeaveRequestBuilder().withDurationStrategy(strategy).build();
        request.getAmountOfDays();
        Mockito.verify(strategy).getAmountOfDays();
    }

    @Test
    @SuppressWarnings("PMD")
    public void testOverlapsAssignment() {
        Interval interval = new Interval(D_2011_04_05, D_2011_04_06);
        LeaveRequestDurationStrategy strategy = Mockito.mock(LeaveRequestDurationStrategy.class);
        LeaveRequest request = new LeaveRequestBuilder().withDurationStrategy(strategy).build();
        request.overlapsAssignment(interval);
        Mockito.verify(strategy).overlapsInterval(interval);
    }

    @Test
    @SuppressWarnings("PMD")
    public void testOverlapsWithLeaveRequestIntersectingInTheEnd() {
        LeaveRequestDurationStrategy strategy = Mockito.mock(LeaveRequestDurationStrategy.class);
        LeaveRequest leaveReq1 = new LeaveRequestBuilder().withDurationStrategy(strategy).build();
        LeaveRequest leaveReq2 = new LeaveRequestBuilder().build();
        leaveReq1.overlapsWith(leaveReq2);
        Mockito.verify(strategy).overlapsWith(leaveReq2);
    }

    @Test
    public void testValidateEmployeeWhenRequestingLessDaysThanSpecified() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            .withType(new LeaveRequestTypeBuilder().withMinLimit(7).build())
            .withInterval(D_2011_04_05, D_2011_04_08)
            .build();
        // @formatter:on
        Assert.assertFalse("Licencia de 4 dias no debe ser valida (minimo 7)",
                request.isValidFor(Mockito.mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenRequestingMoreDaysThanSpecified() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            .withType(new LeaveRequestTypeBuilder().withMaxLimit(7).build())
            .withInterval(D_2011_04_05, D_2011_04_13)
            .build();
        // @formatter:on
        Assert.assertFalse("Licencia de 9 dias no debe ser valida (maximo 7)",
                request.isValidFor(Mockito.mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenRequestingTheMinimumPossibleInterval() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            .withType(new LeaveRequestTypeBuilder().withMinLimit(2).build())
            .withInterval(D_2011_04_05, D_2011_04_06)      // 2 dias de licencia
            .build();
        // @formatter:on
        Assert.assertTrue("La validacion de la licencia fallo (deberia validarla correctamente)",
                request.isValidFor(Mockito.mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenRequestingTheMaximumPossibleInterval() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            .withType(new LeaveRequestTypeBuilder().withMaxLimit(7).build())
            .withInterval(D_2011_04_05, D_2011_04_11)      // 7 dias de licencia
            .build();
        // @formatter:on
        Assert.assertTrue("La validacion de la licencia fallo (deberia validarla correctamente)",
                request.isValidFor(Mockito.mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenRequestingRightAmountOfDays() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            .withType(new LeaveRequestTypeBuilder().withMinLimit(2).withMaxLimit(7).build())
            .withInterval(D_2011_04_05, D_2011_04_08)      // 4 dias de licencia
            .build();
        // @formatter:on
        Assert.assertTrue("La validacion de la licencia fallo (deberia validarla correctamente)",
                request.isValidFor(Mockito.mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenAlreadyRequestedAllPossibleDays() {
        int maxDays = 15;
        Employee empl = Mockito.mock(Employee.class);
        LeaveRequestCustomType leaveReqType = new LeaveRequestTypeBuilder().withMaxDaysInYear(maxDays).build();
        LeaveRequest request = new LeaveRequestBuilder().withType(leaveReqType)
                .withInterval(D_2011_04_05, D_2011_04_08).build();
        Mockito.when(empl.daysRequestedInYear(leaveReqType, 2011)).thenReturn(maxDays);
        Assert.assertFalse("la validacion de la licencia fallo", request.isValidFor(empl));
    }

    @Test
    public void testValidateEmployeeWhenAlreadyRequestedForTheSameDays() {
        Employee empl = Mockito.mock(Employee.class);
        LeaveRequest request1 = new LeaveRequestBuilder().withType(new LeaveRequestTypeBuilder().build())
                .withInterval(D_2011_04_05, D_2011_04_08).build();
        LeaveRequest request2 = new LeaveRequestBuilder().withType(new LeaveRequestTypeBuilder().build())
                .withInterval(D_2011_04_06, D_2011_04_11).build();
        Set<LeaveRequest> reqs = new HashSet<LeaveRequest>();
        reqs.add(request1);
        Mockito.when(empl.getLeaveRequests()).thenReturn(reqs);
        Assert.assertFalse("la validacion de la licencia fallo", request2.isValidFor(empl));
        Mockito.verify(empl).getLeaveRequests();
    }

    @Test
    public void testIsLeaveRequest() {
        LeaveRequest anyLeaveRequest = new LeaveRequestBuilder().build();
        Assert.assertTrue("una licencia ES una licencia", anyLeaveRequest.isLeaveRequest());
    }
}