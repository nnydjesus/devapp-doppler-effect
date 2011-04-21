package ar.edu.unq.dopplereffect.salaries;

import java.util.List;

public class SalarySpecificationBuilder {

    private transient SalarySpecification salaryConstraints;

    public SalarySpecificationBuilder() {
        salaryConstraints = new SalarySpecification();
    }

    public SalarySpecificationBuilder withMinSalary(final int minSalary) {
        salaryConstraints.setMinSalary(minSalary);
        return this;
    }

    public SalarySpecificationBuilder withMaxSalary(final int maxSalary) {
        salaryConstraints.setMaxSalary(maxSalary);
        return this;
    }

    public SalarySpecificationBuilder withPercentages(final List<Integer> percentages) {
        salaryConstraints.setPercentages(percentages);
        return this;
    }

    public SalarySpecification build() {
        return salaryConstraints;
    }
}
