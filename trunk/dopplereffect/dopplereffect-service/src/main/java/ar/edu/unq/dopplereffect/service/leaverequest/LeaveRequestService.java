package ar.edu.unq.dopplereffect.service.leaverequest;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.service.Service;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;

public interface LeaveRequestService extends Service {

    void newLeaveRequest(LeaveRequestDTO leaveReqDTO);

    void deleteLeaveRequest(LeaveRequestViewDTO leaveReqDTO);

    void updateLeaveRequest(LeaveRequestDTO leaveReqDTO);

    List<LeaveRequestViewDTO> searchAllLeaveRequests();

    /**
     * Retorna aquellas licencias que esten en una fecha dada, para un empleado
     * dado.
     */
    List<LeaveRequestViewDTO> searchAllByDateAndEmployee(Date date, String employeeFirstName, String employeeLastName);

    List<LeaveRequestViewDTO> searchAllByDate(Date date);

    List<LeaveRequestViewDTO> searchAllByEmployee(String firstName, String lastName);

    LeaveRequestDTO createEditDTO(LeaveRequestViewDTO viewDTO);

    List<String> searchAllReasons();

    List<LeaveRequestViewDTO> searchAllByReasonAndEmployee(String reason, EmployeeViewDTO employee);

    List<LeaveRequestViewDTO> searchLeaveRequestsContainingDate(DateTime searchByDate);

}
