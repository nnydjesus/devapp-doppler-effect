package ar.edu.unq.dopplereffect.leaverequests;

import static ar.edu.unq.dopplereffect.helpers.DateHelpers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.Interval;
import org.junit.Test;

import ar.edu.unq.dopplereffect.builders.leaverequests.IntervalDurationStrategyBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.LeaveRequestBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.LeaveRequestCustomTypeBuilder;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.employees.EmployeeTimeCalculator;
import ar.edu.unq.dopplereffect.time.DurationStrategy;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public class LeaveRequestTest {

    @Test
    public void testIncludesDay() {
        // GIVEN
        DurationStrategy strategy = mock(DurationStrategy.class);
        LeaveRequest request = new LeaveRequestBuilder().withDurationStrategy(strategy).build();
        // WHEN
        request.includesDay(D_2011_04_13);
        // THEN
        verify(strategy).includesDay(D_2011_04_13);
    }

    @Test
    public void testAmountOfDays() {
        // GIVEN
        DurationStrategy strategy = mock(DurationStrategy.class);
        LeaveRequest request = new LeaveRequestBuilder().withDurationStrategy(strategy).build();
        // WHEN
        request.getAmountOfDays();
        // THEN
        verify(strategy).getAmountOfDays();
    }

    @Test
    public void testOverlapsAssignment() {
        // GIVEN
        Interval interval = new Interval(D_2011_04_05, D_2011_04_06);
        DurationStrategy strategy = mock(DurationStrategy.class);
        LeaveRequest request = new LeaveRequestBuilder().withDurationStrategy(strategy).build();
        // WHEN
        request.overlapsAssignment(interval);
        // THEN
        verify(strategy).overlapsInterval(interval);
    }

    @Test
    public void testOverlapsWithLeaveRequestIntersectingInTheEnd() {
        // GIVEN
        DurationStrategy strategy = mock(DurationStrategy.class);
        LeaveRequest leaveReq1 = new LeaveRequestBuilder().withDurationStrategy(strategy).build();
        LeaveRequest leaveReq2 = new LeaveRequestBuilder().build();
        // WHEN
        leaveReq1.overlapsWith(leaveReq2);
        // THEN
        verify(strategy).overlapsWith(leaveReq2);
    }

    @Test
    public void testValidateEmployeeWhenRequestingLessDaysThanSpecified() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            .withType(new LeaveRequestCustomTypeBuilder().withMinLimit(7).build())
            .withDurationStrategy(new IntervalDurationStrategyBuilder().withInterval(D_2011_04_05, D_2011_04_08).build())
            .build();
        // @formatter:on
        assertFalse("Licencia de 4 dias no debe ser valida (minimo 7)", request.isValidFor(mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenRequestingMoreDaysThanSpecified() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            .withType(new LeaveRequestCustomTypeBuilder().withMaxLimit(7).build())
            .withDurationStrategy(new IntervalDurationStrategyBuilder().withInterval(D_2011_04_05, D_2011_04_13).build())
            .build();
        // @formatter:on
        assertFalse("Licencia de 9 dias no debe ser valida (maximo 7)", request.isValidFor(mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenRequestingTheMinimumPossibleInterval() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            // 2 dias de licencia
            .withType(new LeaveRequestCustomTypeBuilder().withMinLimit(2).build())
            .withDurationStrategy(new IntervalDurationStrategyBuilder().withInterval(D_2011_04_05, D_2011_04_06).build())
            .build();
        // @formatter:on
        assertTrue("La validacion de la licencia fallo (deberia validarla correctamente)",
                request.isValidFor(mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenRequestingTheMaximumPossibleInterval() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            // 7 dias de licencia
            .withType(new LeaveRequestCustomTypeBuilder().withMaxLimit(7).build())
            .withDurationStrategy(new IntervalDurationStrategyBuilder().withInterval(D_2011_04_05, D_2011_04_11).build())
            .build();
        // @formatter:on
        assertTrue("La validacion de la licencia fallo (deberia validarla correctamente)",
                request.isValidFor(mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenRequestingRightAmountOfDays() {
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            // 4 dias de licencia
            .withType(new LeaveRequestCustomTypeBuilder().withMinLimit(2).withMaxLimit(7).build())
            .withDurationStrategy(new IntervalDurationStrategyBuilder().withInterval(D_2011_04_05, D_2011_04_08).build())
            .build();
        // @formatter:on
        assertTrue("La validacion de la licencia fallo (deberia validarla correctamente)",
                request.isValidFor(mock(Employee.class)));
    }

    @Test
    public void testValidateEmployeeWhenAlreadyRequestedAllPossibleDays() {
        int maxDays = 15;
        Employee empl = mock(Employee.class);
        EmployeeTimeCalculator calculator = mock(EmployeeTimeCalculator.class);
        LeaveRequestCustomType leaveReqType = new LeaveRequestCustomTypeBuilder().withMaxDaysInYear(maxDays)
                .withEmployeeTimeCalculator(calculator).build();
        LeaveRequest request = new LeaveRequestBuilder()
                .withType(leaveReqType)
                .withDurationStrategy(
                        new IntervalDurationStrategyBuilder().withInterval(D_2011_04_05, D_2011_04_08).build()).build();
        when(calculator.daysRequestedInYear(empl, leaveReqType, 2011)).thenReturn(maxDays);
        assertFalse("la validacion de la licencia fallo", request.isValidFor(empl));
    }

    @Test
    public void testValidateEmployeeWhenAlreadyRequestedForTheSameDays() {
        // GIVEN
        Employee empl = mock(Employee.class);
        LeaveRequest request1 = new LeaveRequestBuilder().withDurationStrategy(
                new IntervalDurationStrategyBuilder().withInterval(D_2011_04_05, D_2011_04_08).build()).build();
        LeaveRequest request2 = new LeaveRequestBuilder().withDurationStrategy(
                new IntervalDurationStrategyBuilder().withInterval(D_2011_04_06, D_2011_04_11).build()).build();
        Set<LeaveRequest> reqs = new HashSet<LeaveRequest>();
        // WHEN
        reqs.add(request1);
        when(empl.getLeaveRequests()).thenReturn(reqs);
        // THEN
        assertFalse("la validacion de la licencia fallo", request2.isValidFor(empl));
        verify(empl).getLeaveRequests();
    }

    @Test
    public void testIsLeaveRequest() {
        LeaveRequest anyLeaveRequest = new LeaveRequestBuilder().build();
        assertTrue("una licencia ES una licencia", anyLeaveRequest.isLeaveRequest());
    }

    @Test
    public void testGetSuperpositionDays() {
        // GIVEN
        LeaveRequest lreq = new LeaveRequestBuilder().withDurationStrategy(
                new IntervalDurationStrategyBuilder().withInterval(D_2011_04_05, D_2011_04_08).build()).build();
        IntervalDurationStrategy ids = mock(IntervalDurationStrategy.class);
        // WHEN
        when(ids.getStartDate()).thenReturn(D_2011_04_05);
        when(ids.getEndDate()).thenReturn(D_2011_04_08);
        int superpositionDays = lreq.getSuperpositionDaysWith(ids);
        // THEN
        assertEquals("los dias superpuestos fallaron", 4, superpositionDays);
    }
}