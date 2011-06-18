package ar.edu.unq.dopplereffect.service.leaverequest;

import static org.mockito.Mockito.*;

import java.text.SimpleDateFormat;

import ar.edu.unq.dopplereffect.builders.leaverequests.LeaveRequestCustomTypeBuilder;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.persistence.employee.EmployeeRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.leaverequest.LeaveRequestTypeRepositoryImpl;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;

public class LeaveRequestHelpers {

    private LeaveRequestHelpers() {
    }

    public static LeaveRequestDTO createValidLeaveRequestDTOFor(final LeaveRequestServiceImpl service) throws Exception {
        return createValidLeaveRequestDTOFor(service, mock(Employee.class));
    }

    public static LeaveRequestDTO createValidLeaveRequestDTOFor(final LeaveRequestServiceImpl service,
            final Employee empMock) throws Exception {
        // datos necesarios, y validos
        LeaveRequestDTO result = new LeaveRequestDTO();
        result.setReason("XXXX");
        result.setDurationType("Interval");
        result.setStartDate(new SimpleDateFormat("yyyy-mm-dd").parse("2011-06-01"));
        result.setEndDate(new SimpleDateFormat("yyyy-mm-dd").parse("2011-06-10"));
        EmployeeViewDTO employee = new EmployeeViewDTO();
        employee.setDni(12345678);
        result.setEmployee(employee);
        // mock de dependencias
        LeaveRequestTypeRepositoryImpl leaveRequestTypeRepo = mock(LeaveRequestTypeRepositoryImpl.class);
        EmployeeRepositoryImpl employeeRepo = mock(EmployeeRepositoryImpl.class);
        service.setLeaveRequestTypeRepo(leaveRequestTypeRepo);
        service.getEmployeeService().setEmployeeRepo(employeeRepo);
        when(leaveRequestTypeRepo.searchByReason("XXXX")).thenReturn(new LeaveRequestCustomTypeBuilder().build());
        when(employeeRepo.searchByDni(12345678)).thenReturn(empMock);
        return result;
    }
}
