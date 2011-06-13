package ar.edu.unq.dopplereffect.service.employee;

import java.util.List;

import ar.edu.unq.dopplereffect.service.Service;

public interface EmployeeService extends Service {

    void newEmployee(EmployeeDTO employee);

    void deleteEmployee(EmployeeViewDTO employee);

    void updateEmployee(EmployeeDTO employee);

    List<EmployeeViewDTO> searchAllByExample(EmployeeViewDTO theExample);

    List<EmployeeViewDTO> searchAllEmployees();

    EmployeeDetailDTO getDetailForEmployee(EmployeeViewDTO employeeViewDTO);

    EmployeeDTO createEditDTO(EmployeeViewDTO viewDTO);

}