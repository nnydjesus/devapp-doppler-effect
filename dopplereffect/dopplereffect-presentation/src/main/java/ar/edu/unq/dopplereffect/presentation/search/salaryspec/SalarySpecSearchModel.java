package ar.edu.unq.dopplereffect.presentation.search.salaryspec;

import java.util.List;

import ar.edu.unq.dopplereffect.presentation.search.SearchByExampleModel;
import ar.edu.unq.dopplereffect.service.DTO;
import ar.edu.unq.dopplereffect.service.salaryspec.SalarySpecDTO;
import ar.edu.unq.dopplereffect.service.salaryspec.SalarySpecService;

public class SalarySpecSearchModel extends SearchByExampleModel<SalarySpecDTO> {

    private static final long serialVersionUID = 3767171379328961777L;

    private SalarySpecService service;

    public SalarySpecSearchModel() {
        super(SalarySpecDTO.class);
    }

    @Override
    public Class<SalarySpecDTO> getEntityType() {
        return SalarySpecDTO.class;
    }

    public int getSearchByYear() {
        return this.getExample().getYear();
    }

    public void setSearchByYear(final int year) {
        this.getExample().setYear(year);
    }

    public SalarySpecService getService() {
        return service;
    }

    public void setService(final SalarySpecService service) {
        this.service = service;
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
    public List<SalarySpecDTO> searchByExample(final SalarySpecDTO example) {
        return this.getService().searchAllByExample(example);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SalarySpecDTO createEditDTO(final SalarySpecDTO viewDTO) {
        // en este caso se usa el mismo dto
        return viewDTO;
    }
}
