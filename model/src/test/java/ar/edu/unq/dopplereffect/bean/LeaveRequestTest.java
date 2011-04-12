package ar.edu.unq.dopplereffect.bean;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import ar.edu.unq.dopplereffect.configuration.Configuration;

public class LeaveRequestTest {

    private static final DateTime D_2011_04_05 = new DateTime("2011-04-05"), D_2011_04_08 = new DateTime("2011-04-08");

    @Test
    public void testIncludesStartDate() {
        LeaveRequest request = new LeaveRequestBuilder().withStartDate(D_2011_04_05).withEndDate(D_2011_04_08).build();
        Assert.assertTrue(request.includesDay(D_2011_04_05));
    }

    @Test
    public void testIncludesEndDate() {
        LeaveRequest request = new LeaveRequestBuilder().withStartDate(D_2011_04_05).withEndDate(D_2011_04_08).build();
        Assert.assertTrue(request.includesDay(D_2011_04_08));
    }

    @Test
    public void testIncludesIntermediateDate() {
        LeaveRequest request = new LeaveRequestBuilder().withStartDate(D_2011_04_05).withEndDate(D_2011_04_08).build();
        DateTime middle1 = new DateTime("2011-04-06");
        DateTime middle2 = new DateTime("2011-04-06");
        Assert.assertTrue(request.includesDay(middle1));
        Assert.assertTrue(request.includesDay(middle2));
    }

    @Test
    public void testAmountofDays() {
        LeaveRequest request = new LeaveRequestBuilder().withStartDate(D_2011_04_05).withEndDate(D_2011_04_08).build();
        Assert.assertEquals("La cantidad de dias fallo", 4, request.getAmountOfDays());
    }

    @Test
    public void testValidateEmployeeWhenRequestingShortTimeForHolidays() {
        Configuration conf = Mockito.mock(Configuration.class);
        Mockito.when(conf.holidayMinimumPeriod()).thenReturn(7);
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            .withType(new HolidayLeaveRequest(conf))
            .withStartDate(D_2011_04_05)
            .withEndDate(D_2011_04_08)
            .build();
        // @formatter:on
        Assert.assertFalse("Licencia de 4 dias no debe ser valida (minimo 7)",
                request.isValidFor(Mockito.mock(Employee.class)));
    }
}
