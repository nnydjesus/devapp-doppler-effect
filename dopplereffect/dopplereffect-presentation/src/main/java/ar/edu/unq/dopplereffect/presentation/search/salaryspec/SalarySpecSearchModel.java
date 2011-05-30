package ar.edu.unq.dopplereffect.presentation.search.salaryspec;

import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.salaries.SalarySpecification;
import ar.edu.unq.dopplereffect.service.Service;

public class SalarySpecSearchModel extends SearchModel<SalarySpecification> {

    private static final long serialVersionUID = 3767171379328961777L;

    private Service<SalarySpecification> service;

    private transient SalarySpecification example;

    public SalarySpecSearchModel() {
        super(SalarySpecification.class);
        example = this.createExample();
    }

    public void setSearchByYear(final int year) {
        example.setYear(year);
    }

    public int getSearchByYear() {
        return example.getYear();
    }

    @Override
    public void setService(final Service<SalarySpecification> service) {
        this.service = service;
    }

    @Override
    public Service<SalarySpecification> getService() {
        return service;
    }

}
