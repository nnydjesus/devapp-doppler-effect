package ar.edu.unq.dopplereffect.bean;

import java.util.List;

public class SalaryConstraintsBuilder {

    private transient SalaryConstraints salaryConstraints;

    public SalaryConstraintsBuilder() {
        salaryConstraints = new SalaryConstraints();
    }

    public SalaryConstraintsBuilder withMinSalary(final int minSalary) {
        salaryConstraints.setMinSalary(minSalary);
        return this;
    }

    public SalaryConstraintsBuilder withMaxSalary(final int maxSalary) {
        salaryConstraints.setMaxSalary(maxSalary);
        return this;
    }

    public SalaryConstraintsBuilder withPercentages(final List<Integer> percentages) {
        salaryConstraints.setPercentages(percentages);
        return this;
    }

    public SalaryConstraints build() {
        return salaryConstraints;
    }
}
