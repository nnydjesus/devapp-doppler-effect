package ar.edu.unq.dopplereffect.salaries;

import java.util.List;

public class SalarySpecificationBuilder {

    private transient SalarySpecification salarySpecification;

    public SalarySpecificationBuilder() {
        salarySpecification = new SalarySpecification();
    }

    public SalarySpecificationBuilder withMinSalary(final int minSalary) {
        salarySpecification.setMinSalary(minSalary);
        return this;
    }

    public SalarySpecificationBuilder withMaxSalary(final int maxSalary) {
        salarySpecification.setMaxSalary(maxSalary);
        return this;
    }

    public SalarySpecificationBuilder withPercentages(final List<Integer> percentages) {
        salarySpecification.setPercentages(percentages);
        return this;
    }

    public SalarySpecification build() {
        return salarySpecification;
    }
}
