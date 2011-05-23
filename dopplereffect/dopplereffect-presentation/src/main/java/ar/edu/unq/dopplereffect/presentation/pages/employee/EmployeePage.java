package ar.edu.unq.dopplereffect.presentation.pages.employee;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.presentation.pages.basic.EntityPage;

public class EmployeePage extends EntityPage<Employee> {

    public EmployeePage(final EmployeeSearchPage previousPage, final Employee model, final Boolean editMode) {
        super(model, previousPage, editMode);
    }

    public EmployeePage(final EmployeeSearchPage previousPage, final Employee model) {
        super(model, previousPage);
    }

    public EmployeePage(final EmployeeSearchPage previousPage) {
        super(new Employee(), previousPage);
    }

    @Override
    protected void addFields(final Form<Employee> form) {
        form.add(this.getFeedbackPanel());
        form.add(new TextField<String>("firstName"));
        form.add(new TextField<String>("lastName"));
        form.add(new TextField<String>("dni"));
    }

    @Override
    protected String getFormWicketId() {
        return "employeeForm";
    }
}
