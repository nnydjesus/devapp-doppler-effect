package ar.edu.unq.dopplereffect.presentation.search.salaryspec;

import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.dopplereffect.presentation.App;
import ar.edu.unq.dopplereffect.presentation.search.Search;
import ar.edu.unq.dopplereffect.salaries.SalarySpecification;
import ar.edu.unq.dopplereffect.service.Service;

public class SalarySpecSearch extends Search<SalarySpecification> {

    private static final long serialVersionUID = 3767171379328961777L;

    @SpringBean(name = "salarySpecSearch")
    private Service<SalarySpecification> service;

    private transient SalarySpecification example;

    public SalarySpecSearch() {
        super(SalarySpecification.class);
        example = this.createExample();
        App.salarySpecSearch = this;
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
