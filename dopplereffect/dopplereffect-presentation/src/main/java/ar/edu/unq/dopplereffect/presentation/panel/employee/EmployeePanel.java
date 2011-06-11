package ar.edu.unq.dopplereffect.presentation.panel.employee;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.presentation.pages.basic.EntityPanel;
import ar.edu.unq.dopplereffect.service.employee.CareerPlanService;
import ar.edu.unq.dopplereffect.service.employee.EmployeeDTO;

public class EmployeePanel extends EntityPanel<EmployeeDTO> {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.career_plan")
    private CareerPlanService careerPlanService;

    public CareerPlanService getCareerPlanService() {
        return careerPlanService;
    }

    public void setCareerPlanService(final CareerPlanService careerPlanService) {
        this.careerPlanService = careerPlanService;
    }

    public EmployeePanel(final String id, final EmployeeSearchPanel previousPage, final EmployeeDTO model,
            final Boolean editMode) {
        super(id, model, previousPage, editMode);
    }

    public EmployeePanel(final String id, final EmployeeSearchPanel previousPage, final EmployeeDTO model) {
        super(id, model, previousPage);
    }

    public EmployeePanel(final String id, final EmployeeSearchPanel previousPage) {
        super(id, new EmployeeDTO(), previousPage);
    }

    @Override
    protected void addFields(final Form<EmployeeDTO> form) {
        this.addPersonalDataFields(form);
        this.addJoinDateField(form);
        this.addCareerPlanCombo(form);
        this.addCareerPlanLevelCombo(form);
    }

    protected void addPersonalDataFields(final Form<EmployeeDTO> form) {
        form.add(this.getFeedbackPanel());
        form.add(new RequiredTextField<String>("firstName"));
        form.add(new RequiredTextField<String>("lastName"));
        form.add(new RequiredTextField<Integer>("dni"));
        form.add(new TextField<String>("addressStreet"));
        form.add(new TextField<Integer>("addressNumber"));
        form.add(new TextField<String>("addressCity"));
        form.add(new TextField<String>("phoneNumber"));
        form.add(new TextField<String>("email"));
    }

    protected void addCareerPlanCombo(final Form<EmployeeDTO> form) {
        DropDownChoice<CareerPlan> ddc = new DropDownChoice<CareerPlan>("careerPlan",
                Arrays.asList(CareerPlan.values()));
        ddc.setRequired(true);
        form.add(ddc);
    }

    protected void addCareerPlanLevelCombo(final Form<EmployeeDTO> form) {
        List<String> choices = this.getCareerPlanService().searchAllLevels();
        DropDownChoice<String> ddc = new DropDownChoice<String>("careerPlanLevel", choices);
        ddc.setRequired(true);
        form.add(ddc);
    }

    protected void addJoinDateField(final Form<EmployeeDTO> form) {
        DateTextField dateTextField = new DateTextField("joinDate", new PropertyModel<Date>(
                form.getDefaultModelObject(), "joinDate"), new StyleDateConverter(true));
        dateTextField.add(new DatePicker());
        dateTextField.setRequired(true);
        form.add(dateTextField);
    }

    @Override
    protected void beforeConstruct() {
        // x
    }

    @Override
    protected String getFormWicketId() {
        return "employeeForm";
    }
}
