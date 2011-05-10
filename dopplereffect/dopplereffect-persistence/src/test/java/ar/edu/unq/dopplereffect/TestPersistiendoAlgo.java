package ar.edu.unq.dopplereffect;

import org.hibernate.SessionFactory;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class TestPersistiendoAlgo extends AbstractDependencyInjectionSpringContextTests {

    private transient SessionFactory sessionFactory;

    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void testPersistiendoAlgo() {
        // PersistentRepository<CareerData> careerDataRepository = new
        // PersistentRepository<CareerData>();
        // careerDataRepository.setSessionFactory(sessionFactory);
        // CareerData cd = new CareerData(new DateTime(), CareerPlan.TESTER, new
        // CareerPlanLevel("EL MAS CAPO"), 50);
        // careerDataRepository.save(cd);

        // PersistentRepository<Address> addressRepository = new
        // PersistentRepository<Address>();
        // addressRepository.setSessionFactory(sessionFactory);
        // Address address = new Address("Av. Wicket", 123, "Java Land");
        // addressRepository.save(address);

        // PersistentRepository<PersonalData> pdRepository = new
        // PersistentRepository<PersonalData>();
        // pdRepository.setSessionFactory(sessionFactory);
        // Address address = new Address("Calle falsa", 123, "Java Land");
        // PersonalData pd = new PersonalData("Pepito", address);
        // pdRepository.save(pd);

        // PersistentRepository<EmployeeData> edRepository = new
        // PersistentRepository<EmployeeData>();
        // edRepository.setSessionFactory(sessionFactory);
        // Address address = new Address("Av. Spring", 123, "Java Land");
        // EmployeeData pd = new EmployeeData(21234567, "Perez", "Pepito",
        // address);
        // edRepository.save(pd);

        // PersistentRepository<Employee> employeeRepo = new
        // PersistentRepository<Employee>();
        // employeeRepo.setSessionFactory(sessionFactory);
        // Address address = new Address("Av. Hibernate", 123, "Java Land");
        // EmployeeData ed = new EmployeeData(21234567, "El mejor", "Empleado",
        // address);
        // CareerData cd = new CareerData(new DateTime(), CareerPlan.FUNCTIONAL,
        // new CareerPlanLevel("LA DESCOSE"), 50);
        // Employee employee = new Employee(ed, cd);
        // employeeRepo.save(employee);

    }

    @Override
    protected String[] getConfigLocations() {
        // return new String[] { "application-context.xml" };
        return null;
    }
}
