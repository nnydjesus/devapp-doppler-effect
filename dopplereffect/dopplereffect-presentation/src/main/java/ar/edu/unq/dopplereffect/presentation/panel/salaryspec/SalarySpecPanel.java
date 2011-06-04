package ar.edu.unq.dopplereffect.presentation.panel.salaryspec;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.presentation.pages.basic.EntityPanel;
import ar.edu.unq.dopplereffect.salaries.SalarySpecification;
import ar.edu.unq.dopplereffect.service.ServiceImpl;

public class SalarySpecPanel extends EntityPanel<SalarySpecification> {

    private static final long serialVersionUID = -6555172387296650022L;

    @SpringBean(name = "careerPlanLevelService")
    private ServiceImpl<CareerPlanLevel> careerPlanLevelService;

    public SalarySpecPanel(final String id, final SalarySpecSearchPanel previousPage, final SalarySpecification model,
            final Boolean editMode) {
        super(id, model, previousPage, editMode);
    }

    public SalarySpecPanel(final String id, final SalarySpecSearchPanel previousPage, final SalarySpecification model) {
        super(id, model, previousPage);
    }

    public SalarySpecPanel(final String id, final SalarySpecSearchPanel previousPage) {
        super(id, new SalarySpecification(), previousPage);
    }

    @Override
    protected void addFields(final Form<SalarySpecification> form) {
        form.add(this.getFeedbackPanel());
        form.add(new RequiredTextField<Integer>("year"));
        form.add(new RequiredTextField<Float>("minSalary"));
        form.add(new RequiredTextField<Float>("maxSalary"));
        this.addCareerPlanCombo(form);
        this.addCareerPlanLevelCombo(form);
    }

    protected void addCareerPlanCombo(final Form<SalarySpecification> form) {
        PropertyModel<CareerPlan> pm = new PropertyModel<CareerPlan>(form.getDefaultModelObject(), "plan");
        DropDownChoice<CareerPlan> ddc = new DropDownChoice<CareerPlan>("careerPlan", pm, Arrays.asList(CareerPlan
                .values()));
        ddc.setRequired(true);
        form.add(ddc);
    }

    protected void addCareerPlanLevelCombo(final Form<SalarySpecification> form) {
        PropertyModel<CareerPlanLevel> pm = new PropertyModel<CareerPlanLevel>(form.getDefaultModelObject(), "level");
        DropDownChoice<CareerPlanLevel> ddc = new DropDownChoice<CareerPlanLevel>("careerPlanLevel", pm, this
                .getCareerPlanLevelService().searchAll());
        ddc.setRequired(true);
        form.add(ddc);
    }

    @Override
    protected String getFormWicketId() {
        return "salarySpecForm";
    }

    @Override
    protected void beforeConstruct() {
        // x
    }

    public ServiceImpl<CareerPlanLevel> getCareerPlanLevelService() {
        return careerPlanLevelService;
    }

    public void setCareerPlanLevelService(final ServiceImpl<CareerPlanLevel> careerPlanLevelService) {
        this.careerPlanLevelService = careerPlanLevelService;
    }

}
