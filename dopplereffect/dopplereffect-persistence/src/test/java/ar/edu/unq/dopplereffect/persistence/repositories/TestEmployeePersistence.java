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
import ar.edu.unq.dopplereffect.persistence.util.SpringPersistenceTest;

public class TestEmployeePersistence extends SpringPersistenceTest {

    @Autowired
    private EmployeeRepositoryImpl employeeRepository;

    @Test
    public void testSave() {
        Address address = new Address("Av. Hibernate", 123, "Java Land");
        EmployeeData ed = new EmployeeData(21234567, "El mejor", "Empleado", address);
        CareerData cd = new CareerData(new DateTime(), CareerPlan.FUNCTIONAL, new CareerPlanLevel("LA DESCOSE"), 50);
        Employee employee = new Employee(ed, cd);
        employeeRepository.save(employee);

    }
}
