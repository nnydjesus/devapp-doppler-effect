package ar.edu.unq.dopplereffect.bean;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class LeaveRequestTest {

    // @formatter:off
    private static final DateTime 
            D_2011_04_05 = new DateTime("2011-04-05"), 
            D_2011_04_06 = new DateTime("2011-04-06"),
            D_2011_04_08 = new DateTime("2011-04-08"), 
            D_2011_04_11 = new DateTime("2011-04-11"),
            D_2011_04_13 = new DateTime("2011-04-13");
    // @formatter:on

    @Test
    public void testIncludesStartDate() {
        LeaveRequest request = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_08).build();
        Assert.assertTrue(request.includesDay(D_2011_04_05));
    }

    @Test
    public void testIncludesEndDate() {
        LeaveRequest request = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_08).build();
        Assert.assertTrue(request.includesDay(D_2011_04_08));
    }

    @Test
    public void testIncludesIntermediateDate() {
        LeaveRequest request = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_08).build();
        DateTime middle1 = new DateTime("2011-04-06");
        DateTime middle2 = new DateTime("2011-04-06");
        Assert.assertTrue(request.includesDay(middle1));
        Assert.assertTrue(request.includesDay(middle2));
    }

    @Test
    public void testAmountOfDays() {
        LeaveRequest request = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_08).build();
        Assert.assertEquals("La cantidad de dias fallo", 4, request.getAmountOfDays());
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
        LeaveRequestType leaveReqType = new LeaveRequestTypeBuilder().withMaxDaysInYear(maxDays).build();
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
    public void testOverlapsWithLeaveRequestIntersectingInTheEnd() {
        LeaveRequest req1 = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_08).build();
        LeaveRequest req2 = new LeaveRequestBuilder().withInterval(D_2011_04_08, D_2011_04_11).build();
        Assert.assertTrue("las licencias deberian superponerse", req1.overlapsWith(req2));
    }

    @Test
    public void testOverlapsWithLeaveRequestIntersectingSomeDays() {
        LeaveRequest req1 = new LeaveRequestBuilder().withInterval(D_2011_04_06, D_2011_04_11).build();
        LeaveRequest req2 = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_08).build();
        Assert.assertTrue("las licencias deberian superponerse", req1.overlapsWith(req2));
    }

    @Test
    public void testOverlapsWithLeaveRequestIntersectingAllDays() {
        LeaveRequest req1 = new LeaveRequestBuilder().withInterval(D_2011_04_06, D_2011_04_08).build();
        LeaveRequest req2 = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_11).build();
        Assert.assertTrue("las licencias deberian superponerse", req1.overlapsWith(req2));
    }

    @Test
    public void testOverlapsWithLeaveRequestDontIntersect() {
        LeaveRequest req1 = new LeaveRequestBuilder().withInterval(D_2011_04_05, D_2011_04_06).build();
        LeaveRequest req2 = new LeaveRequestBuilder().withInterval(D_2011_04_08, D_2011_04_11).build();
        Assert.assertFalse("las licencias NO deberian superponerse", req1.overlapsWith(req2));
    }

    @Test
    public void testIsLeaveRequest() {
        LeaveRequest request = new LeaveRequestBuilder().build();
        Assert.assertTrue("", request.isLeaveRequest());
    }
}