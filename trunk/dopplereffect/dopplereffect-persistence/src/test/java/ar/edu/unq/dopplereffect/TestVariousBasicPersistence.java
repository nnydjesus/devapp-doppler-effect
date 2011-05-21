package ar.edu.unq.dopplereffect;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import ar.edu.unq.dopplereffect.employees.CareerData;
import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.persistence.repositories.CareerDataRepositoryImpl;

public class TestVariousBasicPersistence extends AbstractTransactionalDataSourceSpringContextTests {

    @Resource
    private CareerDataRepositoryImpl careerDataRepo;

    public void setCareerDataRepo(final CareerDataRepositoryImpl careerDataRepo) {
        this.careerDataRepo = careerDataRepo;
    }

    public void testX() {
        CareerData cd = new CareerData(new DateTime(), CareerPlan.TESTER, new CareerPlanLevel("EL MAS CAPO"), 50);
        careerDataRepo.save(cd);

        //
        // PersistentRepository<Address> addressRepository = new
        // PersistentRepository<Address>();
        // addressRepository.setSessionFactory(sessionFactory);
        // Address address = new Address("Av. Wicket", 123, "Java Land");
        // addressRepository.save(address);
        //
        // PersistentRepository<PersonalData> pdRepository = new
        // PersistentRepository<PersonalData>();
        // pdRepository.setSessionFactory(sessionFactory);
        // Address address1 = new Address("Calle falsa", 123, "Java Land");
        // PersonalData pd = new PersonalData("Pepito", address1);
        // pdRepository.save(pd);
        //
        // PersistentRepository<EmployeeData> edRepository = new
        // PersistentRepository<EmployeeData>();
        // edRepository.setSessionFactory(sessionFactory);
        // Address address2 = new Address("Av. Spring", 123, "Java Land");
        // EmployeeData pd2 = new EmployeeData(21234567, "Perez", "Pepito",
        // address2);
        // edRepository.save(pd2);
        //
        // PersistentRepository<Employee> employeeRepo = new
        // PersistentRepository<Employee>();
        // employeeRepo.setSessionFactory(sessionFactory);
        // Address address3 = new Address("Av. Hibernate", 123, "Java Land");
        // EmployeeData ed = new EmployeeData(21234567, "El mejor", "Empleado",
        // address3);
        // CareerData cd3 = new CareerData(new DateTime(),
        // CareerPlan.FUNCTIONAL, new CareerPlanLevel("LA DESCOSE"), 50);
        // Employee employee = new Employee(ed, cd3);
        // employeeRepo.save(employee);

    }

    @Override
    protected String[] getConfigLocations() {
        return new String[] { "/spring/data-source.xml", "/spring/transactions.xml", "/spring/repositories.xml" };
        // return new String[] { "/spring/data-source.xml",
        // "/spring/transactions.xml" };
    }
}
