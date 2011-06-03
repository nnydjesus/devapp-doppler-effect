package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.service.ServiceImpl;

public class CareerPlanPanel extends AbstractCallbackPanel {

    private static final long serialVersionUID = -4010881082543221231L;

    @SpringBean(name = "careerPlanLevelService")
    private ServiceImpl<CareerPlanLevel> careerPlanLevelService;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CareerPlanPanel(final String id, final AjaxCallBack parent) {
        super(id, parent, null);
        this.init();
    }

    private void init() {
        this.addCareerPlansListing();
        this.addCareerPlanLevelsListing();
    }

    private void addCareerPlansListing() {
        // TODO
    }

    private void addCareerPlanLevelsListing() {
        // TODO
    }

    public ServiceImpl<CareerPlanLevel> getCareerPlanLevelService() {
        return careerPlanLevelService;
    }

    public void setCareerPlanLevelService(final ServiceImpl<CareerPlanLevel> careerPlanLevelService) {
        this.careerPlanLevelService = careerPlanLevelService;
    }
}
