package ar.edu.unq.dopplereffect.presentation.employee;

import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.presentation.App;
import ar.edu.unq.dopplereffect.presentation.search.Search;
import ar.edu.unq.dopplereffect.service.Service;

public class EmployeeSearch extends Search<Employee> {

    private static final long serialVersionUID = -4428182030184876921L;

    @SpringBean(name = "employeeSearch")
    private Service<Employee> service;

    private Employee example;

    public EmployeeSearch() {
        super(Employee.class);
        example = this.createExample();
        App.employeeSearch = this;
    }

    public String getSearchByName() {
        return example.getFirstName();
    }

    public void setSearchByName(final String aName) {
        example.setFirstName(aName);
    }

    @Override
    public Class<Employee> getEntityType() {
        return Employee.class;
    }

    @Override
    public void setService(final Service<Employee> service) {
        this.service = service;
    }

    @Override
    public Service<Employee> getService() {
        return service;
    }
}
