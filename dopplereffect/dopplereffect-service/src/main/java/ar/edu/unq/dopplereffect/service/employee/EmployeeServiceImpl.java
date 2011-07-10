package ar.edu.unq.dopplereffect.service.employee;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Hibernate;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.dopplereffect.data.Address;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.log.NotLoggable;
import ar.edu.unq.dopplereffect.persistence.employee.CareerPlanLevelRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.employee.EmployeeRepositoryImpl;
import ar.edu.unq.dopplereffect.service.export.ExportService;
import ar.edu.unq.dopplereffect.service.validations.Validator;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final long serialVersionUID = -8896121177198817033L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private EmployeeRepositoryImpl employeeRepo;

    private CareerPlanLevelRepositoryImpl careerPlanLevelRepo;

    private ExportService<Employee> exportService;

    /* *************************** CONSTRUCTORS *************************** */

    /* **************************** ACCESSORS ***************************** */

    public EmployeeRepositoryImpl getEmployeeRepo() {
        return employeeRepo;
    }

    public void setEmployeeRepo(final EmployeeRepositoryImpl employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public CareerPlanLevelRepositoryImpl getCareerPlanLevelRepo() {
        return careerPlanLevelRepo;
    }

    public void setCareerPlanLevelRepo(final CareerPlanLevelRepositoryImpl careerPlanLevelRepo) {
        this.careerPlanLevelRepo = careerPlanLevelRepo;
    }

    public void setExportService(final ExportService<Employee> exportService) {
        this.exportService = exportService;
    }

    public ExportService<Employee> getExportService() {
        return exportService;
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    @Transactional
    public void newEmployee(final EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        this.synchronizeWithDTO(employeeDTO, employee);
        // @formatter:off
        new Validator(employee)
            .notBlank("firstName").notBlank("lastName")
            .notNull("firstName").notNull("lastName");
        new Validator(employee.getPersonalData()).isEmail("email");
        // @formatter:on
        this.getEmployeeRepo().save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(final EmployeeViewDTO employeeDTO) {
        this.getEmployeeRepo().delete(this.getEmployeeRepo().searchByDni(employeeDTO.getDni()));
    }

    @Override
    @Transactional
    public void updateEmployee(final EmployeeDTO employeeDTO) {
        Employee emp = this.getEmployeeRepo().searchByDni(employeeDTO.getDni());
        this.synchronizeWithDTO(employeeDTO, emp);
        this.getEmployeeRepo().update(emp);
    }

    @Override
    @Transactional
    public List<EmployeeViewDTO> searchAllEmployees() {
        return this.convertAll(this.getEmployeeRepo().searchAll());
    }

    @Override
    @Transactional
    public List<EmployeeViewDTO> searchAllByFirstAndLastName(final String firstName, final String lastName) {
        return this.convertAll(this.getEmployeeRepo().searchByFirstAndLastName(firstName, lastName));
    }

    @Override
    @Transactional
    public List<EmployeeViewDTO> searchAllByExample(final EmployeeViewDTO theExample) {
        Employee example = new Employee();
        example.setDni(theExample.getDni());
        example.setFirstName(theExample.getFirstName());
        example.setLastName(theExample.getLastName());
        return this.convertAll(this.getEmployeeRepo().searchByExample(example));
    }

    @Override
    @Transactional
    public List<EmployeeViewDTO> searchEmployeeByName(final String name) {
        return this.convertAll(this.getEmployeeRepo().searchByName(name));
    }

    @Override
    @Transactional
    public EmployeeDetailDTO getDetailForEmployee(final EmployeeViewDTO employeeViewDTO) {
        EmployeeDetailDTO result = new EmployeeDetailDTO();
        Employee original = this.getEmployeeRepo().searchByDni(employeeViewDTO.getDni());
        result.setFirstName(original.getFirstName());
        result.setLastName(original.getLastName());
        result.setAddress(original.getPersonalData().getAddress() == null ? "" : original.getPersonalData()
                .getAddress().toString());
        result.setPhoneNumber(original.getPersonalData().getPhoneNumber());
        result.setEmail(original.getPersonalData().getEmail());
        result.setCareerPlan(original.getCareerPlan().name());
        result.setCareerPlanLevel(original.getCareerData().getLevelName());
        result.setPercentage(original.getPercentage());
        return result;
    }

    @Override
    @Transactional
    public EmployeeDTO createEditDTO(final EmployeeViewDTO employeeViewDTO) {
        Employee emp = this.getEmployeeRepo().searchByDni(employeeViewDTO.getDni());
        EmployeeDTO result = new EmployeeDTO();
        result.setFirstName(emp.getFirstName());
        result.setLastName(emp.getLastName());
        result.setDni(emp.getDni());
        result.setPhoneNumber(emp.getPersonalData().getPhoneNumber());
        result.setEmail(emp.getPersonalData().getEmail());
        if (emp.getPersonalData().getAddress() != null) {
            result.setAddressStreet(emp.getPersonalData().getAddress().getStreet());
            result.setAddressNumber(emp.getPersonalData().getAddress().getNumber());
            result.setAddressCity(emp.getPersonalData().getAddress().getCity());
        }
        result.setCareerPlan(emp.getCareerPlan());
        result.setCareerPlanLevel(emp.getCareerData().getLevelName());
        result.setJoinDate(emp.getCareerData().getJoinDate().toDate());
        result.setPercentage(emp.getCareerData().getPercentage());
        return result;
    }

    @NotLoggable
    public EmployeeViewDTO convert(final Employee employee) {
        EmployeeViewDTO result = new EmployeeViewDTO();
        result.setFirstName(employee.getFirstName());
        result.setLastName(employee.getLastName());
        result.setDni(employee.getDni());
        Hibernate.initialize(employee.getAssignments());
        result.getAssignments().addAll(employee.getAssignments());
        return result;
    }

    @Transactional
    @NotLoggable
    public Employee getEmployeeByDTO(final IEmployeeDTO employeeDTO) {
        return this.getEmployeeRepo().searchByDni(employeeDTO.getDni());
    }

    /* ************************* PRIVATE METHODS ************************** */

    @NotLoggable
    private List<EmployeeViewDTO> convertAll(final List<Employee> employees) {
        List<EmployeeViewDTO> results = new LinkedList<EmployeeViewDTO>();
        for (Employee emp : employees) {
            results.add(this.convert(emp));
        }
        return results;
    }

    @NotLoggable
    private void synchronizeWithDTO(final EmployeeDTO employeeDTO, final Employee emp) {
        emp.setFirstName(employeeDTO.getFirstName());
        emp.setLastName(employeeDTO.getLastName());
        emp.setDni(employeeDTO.getDni());
        emp.getPersonalData().setPhoneNumber(employeeDTO.getPhoneNumber());
        emp.getPersonalData().setEmail(employeeDTO.getEmail());
        emp.getPersonalData().setEmail(employeeDTO.getEmail());
        if (employeeDTO.getAddressStreet() == null) {
            emp.getPersonalData().setAddress(
                    new Address(employeeDTO.getAddressStreet(), employeeDTO.getAddressNumber(), employeeDTO
                            .getAddressCity()));

        }
        emp.getCareerData().setJoinDate(new DateTime(employeeDTO.getJoinDate()));
        emp.getCareerData().setCareerPlan(employeeDTO.getCareerPlan());
        emp.getCareerData().setLevel(this.getCareerPlanLevelRepo().getByName(employeeDTO.getCareerPlanLevel()));
        emp.getCareerData().setPercentage(employeeDTO.getPercentage());
    }

    @Override
    @Transactional
    public File export(final String pathFile) {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("Title", "Employee");
        return exportService.export(pathFile, this.getEmployeeRepo().searchAll(), parameters);
    }
}
