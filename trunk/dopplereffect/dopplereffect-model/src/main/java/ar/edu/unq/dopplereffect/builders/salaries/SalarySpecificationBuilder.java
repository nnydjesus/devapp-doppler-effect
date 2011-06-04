package ar.edu.unq.dopplereffect.builders.salaries;

import java.util.List;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.salaries.SalarySpecification;

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

    public SalarySpecificationBuilder withPlan(final CareerPlan plan) {
        salarySpecification.setPlan(plan);
        return this;
    }

    public SalarySpecificationBuilder withYear(final int year) {
        salarySpecification.setYear(year);
        return this;
    }

    public SalarySpecificationBuilder withLevel(final CareerPlanLevel level) {
        salarySpecification.setLevel(level);
        return this;
    }

    public SalarySpecification build() {
        return salarySpecification;
    }
}
