package ar.edu.unq.dopplereffect.persistence.repositories;

import ar.edu.unq.dopplereffect.employees.Employee;

public class EmployeeRepositoryImpl extends HibernatePersistentRepository<Employee> {

    private static final long serialVersionUID = 1L;

    public EmployeeRepositoryImpl() {
        super(Employee.class);
    }

}
