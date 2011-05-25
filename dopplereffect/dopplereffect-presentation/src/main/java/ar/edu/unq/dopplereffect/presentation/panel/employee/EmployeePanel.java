package ar.edu.unq.dopplereffect.presentation.panel.employee;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.presentation.pages.basic.EntityPanel;

public class EmployeePanel extends EntityPanel<Employee> {

    private static final long serialVersionUID = 1L;

    public EmployeePanel(final String id, final EmployeeSearchPanel previousPage, final Employee model,
            final Boolean editMode) {
        super(id, model, previousPage, editMode);
    }

    public EmployeePanel(final String id, final EmployeeSearchPanel previousPage, final Employee model) {
        super(id, model, previousPage);
    }

    public EmployeePanel(final String id, final EmployeeSearchPanel previousPage) {
        super(id, new Employee(), previousPage);
    }

    @Override
    protected void addFields(final Form<Employee> form) {
        form.add(this.getFeedbackPanel());
        form.add(new TextField<String>("firstName"));
        form.add(new TextField<String>("lastName"));
        form.add(new TextField<String>("dni"));
    }

}
