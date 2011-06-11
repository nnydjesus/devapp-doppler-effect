package ar.edu.unq.dopplereffect.presentation.panel.salaryspec;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.presentation.pages.basic.EntityPanel;
import ar.edu.unq.dopplereffect.service.employee.CareerPlanService;
import ar.edu.unq.dopplereffect.service.salaryspec.SalarySpecDTO;

public class SalarySpecPanel extends EntityPanel<SalarySpecDTO> {

    private static final long serialVersionUID = -6555172387296650022L;

    @SpringBean(name = "service.career_plan")
    private CareerPlanService careerPlanService;

    public CareerPlanService getCareerPlanService() {
        return careerPlanService;
    }

    public void setCareerPlanService(final CareerPlanService careerPlanService) {
        this.careerPlanService = careerPlanService;
    }

    public SalarySpecPanel(final String id, final SalarySpecSearchPanel previousPage, final SalarySpecDTO model,
            final Boolean editMode) {
        super(id, model, previousPage, editMode);
    }

    public SalarySpecPanel(final String id, final SalarySpecSearchPanel previousPage, final SalarySpecDTO model) {
        super(id, model, previousPage);
    }

    public SalarySpecPanel(final String id, final SalarySpecSearchPanel previousPage) {
        super(id, new SalarySpecDTO(), previousPage);
    }

    @Override
    protected void addFields(final Form<SalarySpecDTO> form) {
        form.add(this.getFeedbackPanel());
        form.add(new RequiredTextField<Integer>("year"));
        form.add(new RequiredTextField<Float>("minSalary"));
        form.add(new RequiredTextField<Float>("maxSalary"));
        this.addCareerPlanCombo(form);
        this.addCareerPlanLevelCombo(form);
    }

    protected void addCareerPlanCombo(final Form<SalarySpecDTO> form) {
        PropertyModel<CareerPlan> pm = new PropertyModel<CareerPlan>(form.getDefaultModelObject(), "careerPlan");
        DropDownChoice<CareerPlan> ddc = new DropDownChoice<CareerPlan>("careerPlan", pm, Arrays.asList(CareerPlan
                .values()));
        ddc.setRequired(true);
        form.add(ddc);
    }

    protected void addCareerPlanLevelCombo(final Form<SalarySpecDTO> form) {
        PropertyModel<String> pm = new PropertyModel<String>(form.getDefaultModelObject(), "careerPlanLevel");
        DropDownChoice<String> ddc = new DropDownChoice<String>("careerPlanLevel", pm, this.getCareerPlanService()
                .searchAllLevels());
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
}
