package ar.edu.unq.dopplereffect.presentation.pages.employee;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.presentation.App;
import ar.edu.unq.dopplereffect.presentation.search.Search;
import ar.edu.unq.dopplereffect.service.ServiceImpl;

public class EmployeeSearch extends Search<Employee> {

    private static final long serialVersionUID = -4428182030184876921L;

    private String searchName;

    // por el momento, hackazo para poder acceder al servicio
    private EmployeeSearchPage sourcePage;

    public EmployeeSearch() {
    	super(Employee.class);
        this.setSearchByName("");
    }

    public void setSourcePage(final EmployeeSearchPage page) {
        sourcePage = page;
    }

    private ServiceImpl<Employee> getService() {
        return ((App) sourcePage.getApplication()).getEmployeeService();
    }

    public String getSearchByName() {
        return searchName;
    }

    public void setSearchByName(final String aName) {
    	searchName = aName;
    }

    @Override
    public void save(final Employee employee) {
        this.getService().save(employee);
        this.getResults().add(employee);
    }

    @Override
    public void search() {
        this.getService().getByName(searchName);
    }

    @Override
    public void remove(final Employee employee) {
        this.getResults().remove(employee);
        this.getService().delete(employee);
    }

    @Override
    public void update(final Employee t) {
        this.getService().update(t);
        this.search();
    }

    @Override
    public Employee createExample() {
        throw new UnsupportedOperationException();
    }
}
