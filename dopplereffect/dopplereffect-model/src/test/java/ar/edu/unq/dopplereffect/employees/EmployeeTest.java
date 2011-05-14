package ar.edu.unq.dopplereffect.employees;

import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_01;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_05;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_06;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_08;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_09;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_11;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.D_2011_04_13;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.getDate;
import static ar.edu.unq.dopplereffect.helpers.DateHelpers.getDates;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Test;

import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestBuilder;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestCustomType;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestTypeBuilder;
import ar.edu.unq.dopplereffect.project.ProjectAssignment;

public class EmployeeTest {

    @Test
    public void testEqualityByDNI() {
        Employee empl1 = new EmployeeBuilder().withDNI(123456789).build();
        Employee empl2 = new EmployeeBuilder().withDNI(123456789).build();
        assertEquals("empl1 y empl2 deben ser el mismo empleado", empl1, empl2);
        empl2.setDni(23456789);
        assertFalse("empl1 y empl2 no deberian ser el mismo empleado", empl1.equals(empl2));
    }

    @Test
    public void igualdadNotDefinedForFirstOrLastName() {
        Employee empl1 = new EmployeeBuilder().withFirstName("Juan").withLastName("Perez").withDNI(12345678).build();
        Employee empl2 = new EmployeeBuilder().withFirstName("Juan").withLastName("Perez").withDNI(23456789).build();
        assertFalse("empl1 y empl2 no deberian ser el mismo empleado", empl1.equals(empl2));
    }

    @Test
    public void testChangeSalaryPercentage() {
        Employee empl = new EmployeeBuilder().withPercentage(33).build();
        List<Integer> percentages = Arrays.asList(0, 50, 100);
        empl.changeSalaryPercentage(percentages);
        assertEquals("el porcentaje deberia cambiar de 33 a 50", 50, empl.getPercentage());
    }

    @Test
    public void testDaysRequestedInAYear() {
        Employee empl = new EmployeeBuilder().build();
        LeaveRequestCustomType leaveReqType = new LeaveRequestTypeBuilder().withReason("Holiday").build();
        LeaveRequest sevenDaysReq = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_11)
                .withType(leaveReqType).build();
        LeaveRequest fiveDaysReq = new LeaveRequestBuilder().withInterval(getDate(2011, 02, 26), getDate(2011, 03, 02))
                .withType(leaveReqType).build();
        empl.addAssignment(sevenDaysReq); // 7 dias de vacaciones
        empl.addAssignment(fiveDaysReq); // 5 dias de vacaciones
        assertEquals("la cantidad de dias de licencia fallo", 12, empl.daysRequestedInYear(leaveReqType, 2011));
    }

    @Test
    public void testDaysRequestedInAYearFromAnotherReason() {
        Employee empl = new EmployeeBuilder().build();
        LeaveRequestCustomType holidayLeaveReqType = new LeaveRequestTypeBuilder().withReason("Holiday").build();
        LeaveRequestCustomType movingLeaveReqType = new LeaveRequestTypeBuilder().withReason("Moving").build();
        LeaveRequest sevenDaysReq = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_11)
                .withType(holidayLeaveReqType).build();
        empl.addAssignment(sevenDaysReq); // 7 dias de vacaciones
        assertEquals("la cantidad de dias de licencia fallo", 0, empl.daysRequestedInYear(movingLeaveReqType, 2011));
    }

    @Test
    public void testDaysRequestedInAYearFromAnotherYear() {
        Employee empl = new EmployeeBuilder().build();
        LeaveRequestCustomType holidayLeaveReqType = new LeaveRequestTypeBuilder().withReason("Holiday").build();
        LeaveRequest sevenDaysReq = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_11)
                .withType(holidayLeaveReqType).build();
        empl.addAssignment(sevenDaysReq); // 7 dias de vacaciones
        assertEquals("la cantidad de dias de licencia fallo", 0, empl.daysRequestedInYear(holidayLeaveReqType, 2010));
    }

    @Test
    public void testHasLeaveRequestInADay() {
        Employee empl = new EmployeeBuilder().build();
        LeaveRequest leaveReq = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_11)
                .withType(new LeaveRequestTypeBuilder().build()).build();
        empl.addAssignment(leaveReq);
        for (DateTime date : getDates(D_2011_04_05, D_2011_04_11)) {
            assertTrue("el empleado deberia tener una licencia ese dia", empl.hasLeaveRequestInDay(date));
        }
    }

    @Test
    public void testHasntLeaveRequestInADay() {
        Employee empl = new EmployeeBuilder().build();
        LeaveRequest leaveReq = new LeaveRequestBuilder().withInterval(D_2011_04_06, D_2011_04_08)
                .withType(new LeaveRequestTypeBuilder().build()).build();
        empl.addAssignment(leaveReq);
        assertFalse("el empleado NO deberia tener una licencia ese dia", empl.hasLeaveRequestInDay(D_2011_04_05));
        assertFalse("el empleado NO deberia tener una licencia ese dia", empl.hasLeaveRequestInDay(D_2011_04_09));
    }

    @Test
    public void testGetAssignableForDayWhenThereAreAssignables() {
        Employee empl = new EmployeeBuilder().build();
        LeaveRequest leaveReq = mock(LeaveRequest.class);
        ProjectAssignment projAssignment = mock(ProjectAssignment.class);
        empl.addAssignment(leaveReq);
        empl.addAssignment(projAssignment);
        when(leaveReq.includesDay(D_2011_04_11)).thenReturn(true);
        when(projAssignment.includesDay(D_2011_04_13)).thenReturn(true);
        assertEquals("", leaveReq, empl.getAssignableForDay(D_2011_04_11));
        assertEquals("", projAssignment, empl.getAssignableForDay(D_2011_04_13));
    }

    @Test
    public void testGetAssignableForDayWhenThereAreNotAssignables() {
        Employee empl = new EmployeeBuilder().build();
        DateTime anyDate = new DateTime();
        assertNull("el empleado NO deberia tener ninguna asignacion", empl.getAssignableForDay(anyDate));
    }

    @Test
    public void testIsFreeAtInterval() {
        Employee empl = new EmployeeBuilder().build();
        LeaveRequest req = new LeaveRequestBuilder().withInterval(D_2011_04_06, D_2011_04_08).build();
        empl.addAssignment(req);
        assertTrue("el empleado deberia estar libre en el intervalo dado",
                empl.isFreeAtInterval(new Interval(D_2011_04_01, D_2011_04_05)));
        assertTrue("el empleado deberia estar libre en el intervalo dado",
                empl.isFreeAtInterval(new Interval(D_2011_04_09, D_2011_04_13)));
    }

    @Test
    public void testIsntFreeAtInterval() {
        Employee empl = new EmployeeBuilder().build();
        LeaveRequest req = new LeaveRequestBuilder().withInterval(D_2011_04_08, D_2011_04_11).build();
        empl.addAssignment(req);
        assertFalse("el empleado NO deberia estar libre en el intervalo dado",
                empl.isFreeAtInterval(new Interval(D_2011_04_05, D_2011_04_13)));
        assertFalse("el empleado NO deberia estar libre en el intervalo dado",
                empl.isFreeAtInterval(new Interval(D_2011_04_05, D_2011_04_08)));
        assertFalse("el empleado NO deberia estar libre en el intervalo dado",
                empl.isFreeAtInterval(new Interval(D_2011_04_11, D_2011_04_13)));
    }

    @Test
    public void testGetSeniority() {
        Employee empl1 = new EmployeeBuilder().withJoinDate(new DateTime().minusYears(2).minusDays(1)).build();
        Employee empl2 = new EmployeeBuilder().withJoinDate(new DateTime().minusYears(2)).build();
        Employee empl3 = new EmployeeBuilder().withJoinDate(new DateTime().minusYears(2).plusDays(1)).build();
        assertEquals("la antiguedad del empleado deberia ser de 2 años", 2, empl1.getSeniority());
        assertEquals("la antiguedad del empleado deberia ser de 2 años", 2, empl2.getSeniority());
        assertEquals("la antiguedad del empleado deberia ser de 1 año", 1, empl3.getSeniority());
    }
}
