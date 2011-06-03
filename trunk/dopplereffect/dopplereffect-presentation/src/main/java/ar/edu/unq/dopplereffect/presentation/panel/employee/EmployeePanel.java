package ar.edu.unq.dopplereffect.presentation.panel.employee;

import java.util.Arrays;
import java.util.Date;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.presentation.pages.basic.EntityPanel;
import ar.edu.unq.dopplereffect.service.ServiceImpl;

public class EmployeePanel extends EntityPanel<Employee> {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "careerPlanLevelService")
    private ServiceImpl<CareerPlanLevel> careerPlanLevelService;

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
        this.addPersonalDataFields(form);
        this.addJoinDateField(form);
        this.addCareerPlanCombo(form);
        this.addCareerPlanLevelCombo(form);
    }

    protected void addPersonalDataFields(final Form<Employee> form) {
        form.add(this.getFeedbackPanel());
        form.add(new RequiredTextField<String>("firstName"));
        form.add(new RequiredTextField<String>("lastName"));
        form.add(new RequiredTextField<Integer>("dni"));
        form.add(new RequiredTextField<String>("address.street", new PropertyModel<String>(
                form.getDefaultModelObject(), "personalData.address.street")));
        form.add(new RequiredTextField<Integer>("address.number", new PropertyModel<Integer>(form
                .getDefaultModelObject(), "personalData.address.number")));
        form.add(new RequiredTextField<String>("address.city", new PropertyModel<String>(form.getDefaultModelObject(),
                "personalData.address.city")));
        form.add(new TextField<String>("phoneNumber", new PropertyModel<String>(form.getDefaultModelObject(),
                "personalData.phoneNumber")));
        form.add(new TextField<String>("email", new PropertyModel<String>(form.getDefaultModelObject(),
                "personalData.email")));
    }

    protected void addCareerPlanCombo(final Form<Employee> form) {
        PropertyModel<CareerPlan> pm = new PropertyModel<CareerPlan>(form.getDefaultModelObject(),
                "careerData.careerPlan");
        DropDownChoice<CareerPlan> ddc = new DropDownChoice<CareerPlan>("careerPlan", pm, Arrays.asList(CareerPlan
                .values()));
        ddc.setRequired(true);
        form.add(ddc);
    }

    protected void addCareerPlanLevelCombo(final Form<Employee> form) {
        PropertyModel<CareerPlanLevel> pm = new PropertyModel<CareerPlanLevel>(form.getDefaultModelObject(),
                "careerData.level");
        DropDownChoice<CareerPlanLevel> ddc = new DropDownChoice<CareerPlanLevel>("careerPlanLevel", pm, this
                .getCareerPlanLevelService().searchAll());
        ddc.setRequired(true);
        form.add(ddc);
    }

    protected void addJoinDateField(final Form<Employee> form) {
        StyleDateConverter converter = new StyleDateConverter(true);

        Model<Date> model = new Model<Date>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Date getObject() {
                DateTime joinDate = ((Employee) form.getDefaultModelObject()).getCareerData().getJoinDate();
                return joinDate == null ? null : joinDate.toDate();
            }

            @Override
            public void setObject(final Date object) {
                ((Employee) form.getDefaultModelObject()).getCareerData().setJoinDate(new DateTime(object));
            }
        };
        DateTextField dateTextField = new DateTextField("joinDate", model, converter);
        dateTextField.add(new DatePicker());
        dateTextField.setRequired(true);
        form.add(dateTextField);
    }

    public void setCareerPlanLevelService(final ServiceImpl<CareerPlanLevel> careerPlanLevelService) {
        this.careerPlanLevelService = careerPlanLevelService;
    }

    public ServiceImpl<CareerPlanLevel> getCareerPlanLevelService() {
        return careerPlanLevelService;
    }
}
