package ar.edu.unq.dopplereffect.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ar.edu.unq.dopplereffect.configuration.Configuration;

public class LeaveRequestTest {

    private Date d20110405, d20110408;

    @Before
    public void setUp() throws ParseException {
        d20110405 = new SimpleDateFormat("yyyy.MM.dd").parse("2011.04.05");
        d20110408 = new SimpleDateFormat("yyyy.MM.dd").parse("2011.04.08");
    }

    @Test
    public void testIncludesStartDate() {
        LeaveRequest request = new LeaveRequestBuilder().withStartDate(d20110405).withEndDate(d20110408).build();
        Assert.assertTrue(request.includesDay(d20110405));
    }

    @Test
    public void testIncludesEndDate() {
        LeaveRequest request = new LeaveRequestBuilder().withStartDate(d20110405).withEndDate(d20110408).build();
        Assert.assertTrue(request.includesDay(d20110408));
    }

    @Test
    public void testIncludesIntermediateDate() throws ParseException {
        LeaveRequest request = new LeaveRequestBuilder().withStartDate(d20110405).withEndDate(d20110408).build();
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date middle1 = dateFormat.parse("2011.04.06");
        Date middle2 = dateFormat.parse("2011.04.07");
        Assert.assertTrue(request.includesDay(middle1));
        Assert.assertTrue(request.includesDay(middle2));
    }

    @Test
    public void testAmountofDays() {
        LeaveRequest request = new LeaveRequestBuilder().withStartDate(d20110405).withEndDate(d20110408).build();
        Assert.assertEquals("La cantidad de dias fallo", 4, request.getAmountOfDays());
    }

    @Test
    public void testValidateEmployeeWhenRequestingShortTimeForHolidays() {
        Configuration conf = Mockito.mock(Configuration.class);
        Mockito.when(conf.holidayMinimumPeriod()).thenReturn(7);
        // @formatter:off
        LeaveRequest request = new LeaveRequestBuilder()
            .withType(new HolidayLeaveRequest(conf))
            .withStartDate(d20110405)
            .withEndDate(d20110408)
            .build();
        // @formatter:on
        Assert.assertFalse("Licencia de 4 dias no debe ser valida (minimo 7)",
                request.isValidFor(Mockito.mock(Employee.class)));
    }
}
