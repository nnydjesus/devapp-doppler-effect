package ar.edu.unq.dopplereffect.presentation.search.salaryspec;

import ar.edu.unq.dopplereffect.presentation.search.SearchByExampleModel;
import ar.edu.unq.dopplereffect.salaries.SalarySpecification;
import ar.edu.unq.dopplereffect.service.PersistenceService;

public class SalarySpecSearchModel extends SearchByExampleModel<SalarySpecification> {

    private static final long serialVersionUID = 3767171379328961777L;

    private PersistenceService<SalarySpecification> service;

    public SalarySpecSearchModel() {
        super(SalarySpecification.class);
    }

    @Override
    public Class<SalarySpecification> getEntityType() {
        return SalarySpecification.class;
    }

    public int getSearchByYear() {
        return this.getExample().getYear();
    }

    public void setSearchByYear(final int year) {
        this.getExample().setYear(year);
    }

    @Override
    public PersistenceService<SalarySpecification> getService() {
        return service;
    }

    @Override
    public void setService(final PersistenceService<SalarySpecification> service) {
        this.service = service;
    }
}
