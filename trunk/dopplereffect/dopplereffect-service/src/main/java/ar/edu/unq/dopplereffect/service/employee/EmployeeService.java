package ar.edu.unq.dopplereffect.service.employee;

import java.io.File;
import java.util.List;

import ar.edu.unq.dopplereffect.service.Service;

public interface EmployeeService extends Service {

    void newEmployee(EmployeeDTO employee);

    void deleteEmployee(EmployeeViewDTO employee);

    void updateEmployee(EmployeeDTO employee);

    List<EmployeeViewDTO> searchAllEmployees();

    List<EmployeeViewDTO> searchAllByFirstAndLastName(String firstName, String lastName);

    List<EmployeeViewDTO> searchAllByExample(EmployeeViewDTO theExample);

    List<EmployeeViewDTO> searchEmployeeByName(String name);

    EmployeeDetailDTO getDetailForEmployee(EmployeeViewDTO employeeViewDTO);

    EmployeeDTO createEditDTO(EmployeeViewDTO viewDTO);

    File export(String string, List<? extends IEmployeeDTO> list);

}