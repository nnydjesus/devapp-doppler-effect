package ar.edu.unq.dopplereffect.persistence.leaverequest;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unq.dopplereffect.builders.employees.EmployeeBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.IntervalDurationStrategyBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.LeaveRequestBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.OneDayDurationStrategyBuilder;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.persistence.employee.EmployeeRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.util.SpringPersistenceTest;

public class LeaveRequestPersistenceTest extends SpringPersistenceTest {

    @Autowired
    private LeaveRequestRepositoryImpl repository;

    @Autowired
    private EmployeeRepositoryImpl employeeRepository;

    @Test
    public void testSearchByStartDateAndEmployeePersistingAOneDayLeaveRequest() {
        Employee employee = new EmployeeBuilder().build();
        DateTime theDate = new DateTime();
        // @formatter:off
        LeaveRequest leaveRequest = new LeaveRequestBuilder()
            .withEmployee(employee)
            .withDurationStrategy(new OneDayDurationStrategyBuilder()
                .withDate(theDate).build())
            .build();
       // @formatter:on
        try {
            employeeRepository.save(employee);
            repository.save(leaveRequest);
            //
            repository.searchByStartDateAndEmployee(theDate, employee);
        } catch (UserException exc) {
            Assert.fail();
        }
    }

    @Test
    public void testSearchByStartDateAndEmployeePersistingAMultipleDayLeaveRequest() {
        Employee employee = new EmployeeBuilder().build();
        DateTime startDate = new DateTime();
        DateTime endDate = startDate.plusDays(3);
        // @formatter:off
        LeaveRequest leaveRequest = new LeaveRequestBuilder()
            .withEmployee(employee)
            .withDurationStrategy(new IntervalDurationStrategyBuilder()
                .withStartDate(startDate).withEndDate(endDate).build())
            .build();
       // @formatter:on
        try {
            employeeRepository.save(employee);
            repository.save(leaveRequest);
            //
            repository.searchByStartDateAndEmployee(startDate, employee);
        } catch (UserException exc) {
            Assert.fail();
        }
    }
}
