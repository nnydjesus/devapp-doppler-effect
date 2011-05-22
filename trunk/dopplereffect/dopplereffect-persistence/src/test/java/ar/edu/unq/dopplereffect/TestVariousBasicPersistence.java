package ar.edu.unq.dopplereffect;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import ar.edu.unq.dopplereffect.data.Address;
import ar.edu.unq.dopplereffect.data.PersonalData;
import ar.edu.unq.dopplereffect.employees.CareerData;
import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.employees.EmployeeData;
import ar.edu.unq.dopplereffect.persistence.repositories.CareerDataRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.repositories.HibernatePersistentRepository;

public class TestVariousBasicPersistence extends AbstractTransactionalDataSourceSpringContextTests {

    @Resource
    private CareerDataRepositoryImpl careerDataRepo;

    public void setCareerDataRepo(final CareerDataRepositoryImpl careerDataRepo) {
        this.careerDataRepo = careerDataRepo;
    }

    @Test
    public void testX() {
        CareerData cd = new CareerData(new DateTime(), CareerPlan.TESTER, new CareerPlanLevel("EL MAS CAPO"), 50);
        careerDataRepo.save(cd);

        //
        HibernatePersistentRepository<Address> addressRepository = new HibernatePersistentRepository<Address>();
        // addressRepository.setSessionFactory(sessionFactory);
        Address address = new Address("Av. Wicket", 123, "Java Land");
        // addressRepository.save(address);

        HibernatePersistentRepository<PersonalData> pdRepository = new HibernatePersistentRepository<PersonalData>();
        // pdRepository.setSessionFactory(sessionFactory);
        Address address1 = new Address("Calle falsa", 123, "Java Land");
        PersonalData pd = new PersonalData("Pepito", address1);
        // pdRepository.save(pd);

        HibernatePersistentRepository<EmployeeData> edRepository = new HibernatePersistentRepository<EmployeeData>();
        // edRepository.setSessionFactory(sessionFactory);
        Address address2 = new Address("Av. Spring", 123, "Java Land");
        EmployeeData pd2 = new EmployeeData(21234567, "Perez", "Pepito", address2);
        // edRepository.save(pd2);

        HibernatePersistentRepository<Employee> employeeRepo = new HibernatePersistentRepository<Employee>();
        // employeeRepo.setSessionFactory(sessionFactory);
        Address address3 = new Address("Av. Hibernate", 123, "Java Land");
        EmployeeData ed = new EmployeeData(21234567, "El mejor", "Empleado", address3);
        CareerData cd3 = new CareerData(new DateTime(), CareerPlan.FUNCTIONAL, new CareerPlanLevel("LA DESCOSE"), 50);
        Employee employee = new Employee(ed, cd3);
        // employeeRepo.save(employee);

    }

    @Override
    protected String[] getConfigLocations() {
        return new String[] { "/spring/data-source.xml", "/spring/transactions.xml", "/spring/repositories.xml" };
        // return new String[] { "/spring/data-source.xml",
        // "/spring/transactions.xml" };
    }
}
