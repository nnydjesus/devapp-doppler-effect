package ar.edu.unq.dopplereffect.presentation.employee;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.data.Address;
import ar.edu.unq.dopplereffect.employees.CareerData;
import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.employees.EmployeeData;
import ar.edu.unq.dopplereffect.presentation.App;
import ar.edu.unq.dopplereffect.presentation.search.SearchPanelModel;
import ar.edu.unq.dopplereffect.service.Service;

public class EmployeeSearch extends SearchPanelModel<Employee> {

    private static final long serialVersionUID = -4428182030184876921L;

    @SpringBean(name = "employeeSearch")
    private Service<Employee> service;

    private transient Employee example;

    public EmployeeSearch() {
        super(Employee.class);
        example = this.createExample();
        App.employeeSearch = this;
        this.addTestData();
    }

    private void addTestData() {
        CareerData careerData = new CareerData(new DateTime(), CareerPlan.TESTER, new CareerPlanLevel("Junior"), 50);
        Address address = new Address("Av. XXX", 123, "YYY");
        EmployeeData personalData = new EmployeeData(12345678, "Juan", "Perez", "11-65655665", "juan.perez@x.com",
                address);
        this.getResults().add(new Employee(personalData, careerData));
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
