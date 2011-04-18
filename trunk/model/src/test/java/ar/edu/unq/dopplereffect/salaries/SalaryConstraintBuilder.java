package ar.edu.unq.dopplereffect.salaries;

import java.util.List;

public class SalaryConstraintBuilder {

    private transient SalarySpecification salaryConstraints;

    public SalaryConstraintBuilder() {
        salaryConstraints = new SalarySpecification();
    }

    public SalaryConstraintBuilder withMinSalary(final int minSalary) {
        salaryConstraints.setMinSalary(minSalary);
        return this;
    }

    public SalaryConstraintBuilder withMaxSalary(final int maxSalary) {
        salaryConstraints.setMaxSalary(maxSalary);
        return this;
    }

    public SalaryConstraintBuilder withPercentages(final List<Integer> percentages) {
        salaryConstraints.setPercentages(percentages);
        return this;
    }

    public SalarySpecification build() {
        return salaryConstraints;
    }
}
