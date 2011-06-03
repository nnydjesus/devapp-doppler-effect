package ar.edu.unq.dopplereffect.presentation.employee;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.presentation.search.SearchByExampleModel;
import ar.edu.unq.dopplereffect.service.Service;

public class EmployeeSearchModel extends SearchByExampleModel<Employee> {

    private static final long serialVersionUID = -4428182030184876921L;

    private Service<Employee> service;

    public EmployeeSearchModel() {
        super(Employee.class);
    }

    public String getSearchByName() {
        return this.getExample().getFirstName();
    }

    public void setSearchByName(final String aName) {
        this.getExample().setFirstName(aName);
    }

    @Override
    public Class<Employee> getEntityType() {
        return Employee.class;
    }

    @Override
    public Service<Employee> getService() {
        return service;
    }

    @Override
    public void setService(final Service<Employee> service) {
        this.service = service;
    }
}
