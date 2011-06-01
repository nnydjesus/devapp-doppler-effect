package ar.edu.unq.dopplereffect.presentation.panel.employee;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.joda.time.DateTime;
import org.odlabs.wiquery.ui.datepicker.DatePicker;

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
        form.add(new RequiredTextField<String>("firstName"));
        form.add(new RequiredTextField<String>("lastName"));
        form.add(new RequiredTextField<Integer>("dni"));
        form.add(new TextField<String>("phoneNumber", new PropertyModel<String>(form.getDefaultModelObject(),
                "personalData.phoneNumber")));
        form.add(new RequiredTextField<String>("address.street", new PropertyModel<String>(
                form.getDefaultModelObject(), "personalData.address.street")));
        form.add(new RequiredTextField<Integer>("address.number", new PropertyModel<Integer>(form
                .getDefaultModelObject(), "personalData.address.number")));
        form.add(new RequiredTextField<String>("address.city", new PropertyModel<String>(form.getDefaultModelObject(),
                "personalData.address.city")));
        form.add(new TextField<String>("email", new PropertyModel<String>(form.getDefaultModelObject(),
                "personalData.email")));
        DatePicker<DateTime> datePicker = new DatePicker<DateTime>("joinDate", new PropertyModel<DateTime>(
                form.getDefaultModelObject(), "careerData.joinDate"), DateTime.class);
        datePicker.setDateFormat("yy-mm-dd");
        form.add(datePicker);
    }

}
