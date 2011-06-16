package ar.edu.unq.dopplereffect.employees;

import static ar.edu.unq.dopplereffect.helpers.DateHelpers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.joda.time.Interval;
import org.junit.Test;

import ar.edu.unq.dopplereffect.assignments.Assignable;
import ar.edu.unq.dopplereffect.builders.employees.EmployeeBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.IntervalDurationStrategyBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.LeaveRequestBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.LeaveRequestCustomTypeBuilder;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestCustomType;
import ar.edu.unq.dopplereffect.projects.ProjectAssignment;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public class EmployeeTimeCalculatorTest {

    @Test
    public void testDaysRequestedInAYear() {
        EmployeeTimeCalculator calculator = new EmployeeTimeCalculator();
        Employee empl = new EmployeeBuilder().build();
        LeaveRequestCustomType leaveReqType = new LeaveRequestCustomTypeBuilder().withReason("Holiday").build();
        LeaveRequest sevenDaysReq = new LeaveRequestBuilder()
                .withDurationStrategy(
                        new IntervalDurationStrategyBuilder().withInterval(D_2011_04_05, D_2011_04_11).build())
                .withType(leaveReqType).build();
        LeaveRequest fiveDaysReq = new LeaveRequestBuilder()
                .withDurationStrategy(
                        new IntervalDurationStrategyBuilder()
                                .withInterval(getDate(2011, 02, 26), getDate(2011, 03, 02)).build())
                .withType(leaveReqType).build();
        empl.addAssignment(sevenDaysReq); // 7 dias de vacaciones
        empl.addAssignment(fiveDaysReq); // 5 dias de vacaciones
        assertEquals("la cantidad de dias de licencia fallo", 12,
                calculator.daysRequestedInYear(empl, leaveReqType, 2011));
    }

    @Test
    public void testDaysRequestedInAYearFromAnotherReason() {
        EmployeeTimeCalculator calculator = new EmployeeTimeCalculator();
        Employee empl = new EmployeeBuilder().build();
        LeaveRequestCustomType holidayLeaveReqType = new LeaveRequestCustomTypeBuilder().withReason("Holiday").build();
        LeaveRequestCustomType movingLeaveReqType = new LeaveRequestCustomTypeBuilder().withReason("Moving").build();
        LeaveRequest sevenDaysReq = new LeaveRequestBuilder()
                .withDurationStrategy(
                        new IntervalDurationStrategyBuilder().withInterval(D_2011_04_05, D_2011_04_11).build())
                .withType(holidayLeaveReqType).build();
        empl.addAssignment(sevenDaysReq); // 7 dias de vacaciones
        assertEquals("la cantidad de dias de licencia fallo", 0,
                calculator.daysRequestedInYear(empl, movingLeaveReqType, 2011));
    }

    @Test
    public void testDaysRequestedInAYearFromAnotherYear() {
        EmployeeTimeCalculator calculator = new EmployeeTimeCalculator();
        Employee empl = new EmployeeBuilder().build();
        LeaveRequestCustomType holidayLeaveReqType = new LeaveRequestCustomTypeBuilder().withReason("Holiday").build();
        LeaveRequest sevenDaysReq = new LeaveRequestBuilder()
                .withDurationStrategy(
                        new IntervalDurationStrategyBuilder().withInterval(D_2011_04_05, D_2011_04_11).build())
                .withType(holidayLeaveReqType).build();
        empl.addAssignment(sevenDaysReq); // 7 dias de vacaciones
        assertEquals("la cantidad de dias de licencia fallo", 0,
                calculator.daysRequestedInYear(empl, holidayLeaveReqType, 2010));
    }

    @Test
    public void testIsFreeAtInterval() {
        EmployeeTimeCalculator calculator = new EmployeeTimeCalculator();
        Employee empl = new EmployeeBuilder().build();
        LeaveRequest req = new LeaveRequestBuilder().withDurationStrategy(
                new IntervalDurationStrategyBuilder().withInterval(D_2011_04_06, D_2011_04_08).build()).build();
        empl.addAssignment(req);
        assertTrue("el empleado deberia estar libre en el intervalo dado",
                calculator.isFreeAtInterval(empl, new Interval(D_2011_04_01, D_2011_04_05)));
        assertTrue("el empleado deberia estar libre en el intervalo dado",
                calculator.isFreeAtInterval(empl, new Interval(D_2011_04_09, D_2011_04_13)));
    }

    @Test
    public void testIsntFreeAtInterval() {
        EmployeeTimeCalculator calculator = new EmployeeTimeCalculator();
        Employee empl = new EmployeeBuilder().build();
        LeaveRequest req = new LeaveRequestBuilder().withDurationStrategy(
                new IntervalDurationStrategyBuilder().withInterval(D_2011_04_08, D_2011_04_11).build()).build();
        empl.addAssignment(req);
        assertFalse("el empleado NO deberia estar libre en el intervalo dado",
                calculator.isFreeAtInterval(empl, new Interval(D_2011_04_05, D_2011_04_13)));
        assertFalse("el empleado NO deberia estar libre en el intervalo dado",
                calculator.isFreeAtInterval(empl, new Interval(D_2011_04_05, D_2011_04_08)));
        assertFalse("el empleado NO deberia estar libre en el intervalo dado",
                calculator.isFreeAtInterval(empl, new Interval(D_2011_04_11, D_2011_04_13)));
    }

    @Test
    public void testAvailabilityLevel() {
        EmployeeTimeCalculator calculator = new EmployeeTimeCalculator();
        Employee employee = new EmployeeBuilder().build();
        Assignable assignable1 = mock(Assignable.class);
        Assignable assignable2 = mock(Assignable.class);
        employee.addAssignment(assignable1);
        employee.addAssignment(assignable2);
        IntervalDurationStrategy intervalDS = mock(IntervalDurationStrategy.class);
        when(assignable1.getSuperpositionDaysWith(intervalDS)).thenReturn(3);
        when(assignable2.getSuperpositionDaysWith(intervalDS)).thenReturn(1);
        when(intervalDS.getAmountOfDays()).thenReturn(5);
        int expectedPercentage = 20; // 4 de 5 dias totales
        assertEquals("el nivel de disponibilidad fallo", expectedPercentage,
                calculator.availabilityLevel(employee, intervalDS));
    }

    @Test
    public void testAvailableIntervals() {
        // ESCENARIO :
        // * intervalo : del 1/4 al 13/4
        // * licencia : del 6/4 al 8/4
        // * proyecto : del 11/4 al 13/4
        // * resultado esperado : [1/4~5/4, 9/4~10/4]
        EmployeeTimeCalculator calculator = new EmployeeTimeCalculator();
        Employee employee = new EmployeeBuilder().build();
        LeaveRequest lreq = mock(LeaveRequest.class);
        ProjectAssignment pAssign = mock(ProjectAssignment.class);
        IntervalDurationStrategy interval = new IntervalDurationStrategy(D_2011_04_01, D_2011_04_13);
        when(lreq.includesDay(D_2011_04_06)).thenReturn(true);
        when(lreq.includesDay(D_2011_04_07)).thenReturn(true);
        when(lreq.includesDay(D_2011_04_08)).thenReturn(true);
        when(pAssign.includesDay(D_2011_04_11)).thenReturn(true);
        when(pAssign.includesDay(D_2011_04_12)).thenReturn(true);
        when(pAssign.includesDay(D_2011_04_13)).thenReturn(true);
        employee.addAssignment(lreq);
        employee.addAssignment(pAssign);
        IntervalDurationStrategy expInterval1 = new IntervalDurationStrategy(D_2011_04_01, D_2011_04_05);
        IntervalDurationStrategy expInterval2 = new IntervalDurationStrategy(D_2011_04_09, D_2011_04_10);
        List<IntervalDurationStrategy> result = calculator.getAvailableIntervals(employee, interval);
        assertTrue("", result.contains(expInterval1));
        assertTrue("", result.contains(expInterval2));
    }
}
