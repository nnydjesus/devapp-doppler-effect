package ar.edu.unq.dopplereffect.presentation.search.salaryspec;

import java.util.List;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.service.DTO;
import ar.edu.unq.dopplereffect.service.salaryspec.SalarySpecDTO;
import ar.edu.unq.dopplereffect.service.salaryspec.SalarySpecService;

public class SalarySpecSearchModel extends SearchModel<SalarySpecDTO> {

    private static final long serialVersionUID = 3767171379328961777L;

    private SalarySpecService service;

    private CareerPlan searchByCareerPlan;

    private String searchByCareerPlanLevel;

    public SalarySpecSearchModel() {
        super(SalarySpecDTO.class);
    }

    public SalarySpecService getService() {
        return service;
    }

    public void setService(final SalarySpecService service) {
        this.service = service;
    }

    public CareerPlan getSearchByCareerPlan() {
        return searchByCareerPlan;
    }

    public void setSearchByCareerPlan(final CareerPlan searchByCareerPlan) {
        this.searchByCareerPlan = searchByCareerPlan;
    }

    public String getSearchByCareerPlanLevel() {
        return searchByCareerPlanLevel;
    }

    public void setSearchByCareerPlanLevel(final String searchByCareerPlanLevel) {
        this.searchByCareerPlanLevel = searchByCareerPlanLevel;
    }

    @Override
    protected List<SalarySpecDTO> getAllResultsFromService() {
        return this.getService().searchAllSalarySpecs();
    }

    @Override
    protected <D extends DTO> void callSaveOnService(final D entity) {
        this.getService().newSalarySpecification((SalarySpecDTO) entity);
    }

    @Override
    protected void callRemoveOnService(final SalarySpecDTO entity) {
        this.getService().deleteSalarySpecification(entity);
    }

    @Override
    protected <D extends DTO> void callUpdateOnService(final D entity) {
        this.getService().updateSalarySpecification((SalarySpecDTO) entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SalarySpecDTO createEditDTO(final SalarySpecDTO viewDTO) {
        // en este caso se usa el mismo dto
        return viewDTO;
    }

    @Override
    public void search() {
        this.setResults(this.getService().searchByCareerPlanAndLevel(this.getSearchByCareerPlan(),
                this.getSearchByCareerPlanLevel()));
    }
}
