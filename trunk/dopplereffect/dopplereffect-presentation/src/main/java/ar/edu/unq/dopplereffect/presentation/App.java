package ar.edu.unq.dopplereffect.presentation;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import ar.edu.unq.dopplereffect.presentation.pages.Login;
import ar.edu.unq.dopplereffect.service.EmployeeServiceImpl;

public class App extends WebApplication {

    private EmployeeServiceImpl employeeService;

    @Override
    protected void init() {
        super.init();
        this.getResourceSettings().addResourceFolder("pages");
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return Login.class;
    }

    public EmployeeServiceImpl getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(final EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
}