package ar.edu.unq.dopplereffect.presentation.panel.employee;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.presentation.search.Search;
import ar.edu.unq.dopplereffect.service.Service;

public class EmployeeSearch extends Search<Employee> {

    private static final long serialVersionUID = -4428182030184876921L;

    private String searchName;

    public EmployeeSearch() {
        super(Employee.class);
        this.setSearchByName("");
    }

    // private ServiceImpl<Employee> getService() {
    // return ((App) sourcePage.getApplication()).getEmployeeService();
    // }

    public String getSearchByName() {
        return this.getSearchName();
    }

    public void setSearchByName(final String aName) {
        this.setSearchName(aName);
    }

    @Override
    public void save(final Employee employee) {
        // this.getService().save(employee);
        this.getResults().add(employee);
    }

    @Override
    public void search() {
        // this.getService().getByName(searchName);
    }

    @Override
    public void remove(final Employee employee) {
        this.getResults().remove(employee);
        // this.getService().delete(employee);
    }

    @Override
    public void update(final Employee employee) {
        // this.getService().update(t);
        this.search();
    }

    @Override
    public Employee createExample() {
        throw new UnsupportedOperationException();
    }

    public void setSearchName(final String searchName) {
        this.searchName = searchName;
    }

    public String getSearchName() {
        return searchName;
    }

    @Override
    public void setService(final Service<Employee> service) {
        // throw new UnsupportedOperationException();
    }

    @Override
    public Service<Employee> getService() {
        return null;
        // throw new UnsupportedOperationException();
    }
}
