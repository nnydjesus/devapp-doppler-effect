package ar.edu.unq.dopplereffect.persistence.repositories;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unq.dopplereffect.data.Address;
import ar.edu.unq.dopplereffect.employees.CareerData;
import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.employees.EmployeeData;
import ar.edu.unq.dopplereffect.persistence.employee.CareerPlanLevelRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.employee.EmployeeRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.util.SpringPersistenceTest;

public class EmployeePersistenceTest extends SpringPersistenceTest {

    @Autowired
    private CareerPlanLevelRepositoryImpl careerPlanLevelRepositoryImpl;

    @Autowired
    private EmployeeRepositoryImpl employeeRepository;

    @Test
    public void testSave() {
        Address address = new Address("Av. Hibernate", 123, "Java Land");
        EmployeeData ed = new EmployeeData(21234567, "El mejor", "Empleado", address);
        CareerPlanLevel level = new CareerPlanLevel("LA DESCOSE");
        careerPlanLevelRepositoryImpl.save(level);
        CareerData cd = new CareerData(new DateTime(), CareerPlan.FUNCTIONAL, level, 50);
        Employee employee = new Employee(ed, cd);
        employeeRepository.save(employee);

    }
}
