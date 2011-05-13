package ar.edu.unq.dopplereffect;

import org.hibernate.SessionFactory;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class TestPersistiendoAlgo extends AbstractDependencyInjectionSpringContextTests {

    private transient SessionFactory sessionFactory;

    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void testPersistiendoAlgo() {
        // personal data

        // PersistentRepository<PersonalData> pdRepository = new
        // PersistentRepository<PersonalData>();
        // pdRepository.setSessionFactory(sessionFactory);
        // Address address = new Address("Calle falsa", 123, "Java Land");
        // PersonalData pd = new PersonalData("Pepito", address);
        // pdRepository.save(pd);

        // employee data

        // PersistentRepository<EmployeeData> edRepository = new
        // PersistentRepository<EmployeeData>();
        // edRepository.setSessionFactory(sessionFactory);
        // Address address = new Address("Av. Spring", 123, "Java Land");
        // EmployeeData pd = new EmployeeData(21234567, "Perez", "Pepito",
        // address);
        // edRepository.save(pd);

        // employee

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

        // leave request type

        // PersistentRepository<LeaveRequestType> lrqtRepo = new
        // PersistentRepository<LeaveRequestType>();
        // lrqtRepo.setSessionFactory(this.getSessionFactory());
        // LeaveRequestType lrqt = new
        // LeaveRequestCustomType("Licencia porque juega Boca", 3, 1, 1);
        // lrqtRepo.save(lrqt);

        // one day duration strategy

        // PersistentRepository<DurationStrategy> oneDayDSRepo = new
        // PersistentRepository<DurationStrategy>();
        // oneDayDSRepo.setSessionFactory(this.getSessionFactory());
        // DurationStrategy oneDayDS = new OneDayDurationStrategy(new
        // DateTime());
        // oneDayDSRepo.save(oneDayDS);

        // interval duration strategy

        // PersistentRepository<DurationStrategy> intervalDSRepo = new
        // PersistentRepository<DurationStrategy>();
        // intervalDSRepo.setSessionFactory(this.getSessionFactory());
        // DurationStrategy oneDayDS = new IntervalDurationStrategy(new
        // DateTime(), new DateTime("2011-05-22"));
        // intervalDSRepo.save(oneDayDS);

        // employee con datos y un par de licencias

        // PersistentRepository<Employee> employeeRepo = new
        // PersistentRepository<Employee>();
        // employeeRepo.setSessionFactory(this.getSessionFactory());
        // LeaveRequestType type = new
        // LeaveRequestCustomType("licencia porque juega Boca");
        // DurationStrategy durationStrategy1 = new IntervalDurationStrategy(new
        // DateTime(), new DateTime("2011-05-25"));
        // DurationStrategy durationStrategy2 = new OneDayDurationStrategy(new
        // DateTime("2011-09-06"));
        // LeaveRequest lrq1 = new LeaveRequest(type, durationStrategy1);
        // LeaveRequest lrq2 = new LeaveRequest(type, durationStrategy2);
        // Address address = new Address("Av. Hibernate", 123, "Java Land");
        // EmployeeData personalData = new EmployeeData(12345678, "Juan",
        // "Perez", address);
        // CareerPlanLevel capoLevel = new CareerPlanLevel("CAPO");
        // CareerData careerData = new CareerData(new DateTime("2011-05-12"),
        // CareerPlan.TESTER, capoLevel, 25);
        // Employee employee = new Employee(personalData, careerData);
        // employee.addAssignment(lrq1);
        // employee.addAssignment(lrq2);
        // employeeRepo.save(employee);
    }

    @Override
    protected String[] getConfigLocations() {
        // return new String[] { "application-context.xml" };
        return new String[] {};
    }
}
