package ar.edu.unq.dopplereffect.presentation.panel.salaryspec;

import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.search.salaryspec.SalarySpecSearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.service.employee.CareerPlanService;

public class SalarySpecSearchPanel extends AbstractSearchPanel<SalarySpecSearchModel> {

    private static final long serialVersionUID = 4870066995423669454L;

    @SpringBean(name = "service.career_plan")
    private CareerPlanService careerPlanService;

    public CareerPlanService getCareerPlanService() {
        return careerPlanService;
    }

    public void setCareerPlanService(final CareerPlanService careerPlanService) {
        this.careerPlanService = careerPlanService;
    }

    public SalarySpecSearchPanel(final String id, final AjaxCallBack<Component> parentPage,
            final SalarySpecSearchModel model) {
        super(id, parentPage, model, Arrays.asList("year", "viewPercentages", "careerPlan", "careerPlanLevel"),
                SalarySpecPanel.class);
    }

    @Override
    protected void addInputSearch(final Form<SalarySpecSearchModel> form) {
        DropDownChoice<CareerPlan> ddc1 = new DropDownChoice<CareerPlan>("searchByCareerPlan", Arrays.asList(CareerPlan
                .values()));
        ddc1.setNullValid(true);
        form.add(ddc1);
        DropDownChoice<String> ddc2 = new DropDownChoice<String>("searchByCareerPlanLevel", this.getCareerPlanService()
                .searchAllLevels());
        ddc2.setNullValid(true);
        form.add(ddc2);
    }

    @Override
    protected String getSortName() {
        return "year";
    }

    @Override
    protected String getFormWicketId() {
        return "searchSalarySpecForm";
    }

    @Override
    protected String getNewFromBeanWicketId() {
        return "newSalarySpec";
    }

    @Override
    protected boolean showTitle() {
        return true;
    }
}
