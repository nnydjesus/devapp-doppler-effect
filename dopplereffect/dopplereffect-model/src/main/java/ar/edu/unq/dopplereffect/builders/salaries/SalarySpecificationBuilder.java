package ar.edu.unq.dopplereffect.builders.salaries;

import java.util.Arrays;
import java.util.List;

import ar.edu.unq.dopplereffect.builders.employees.CareerPlanLevelBuilder;
import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.salaries.SalarySpecification;

public class SalarySpecificationBuilder {

    protected transient int minSalary = 2000;

    protected transient int maxSalary = 5000;

    protected transient List<Integer> percentages = Arrays.asList(0, 100);

    protected transient CareerPlan careerPlan = CareerPlan.TESTER;

    protected transient CareerPlanLevel careerPlanLevel = new CareerPlanLevelBuilder().build();

    protected transient int year = 2011;

    public SalarySpecificationBuilder withMinSalary(final int theMinSalary) {
        minSalary = theMinSalary;
        return this;
    }

    public SalarySpecificationBuilder withMaxSalary(final int theMaxSalary) {
        maxSalary = theMaxSalary;
        return this;
    }

    public SalarySpecificationBuilder withPercentages(final List<Integer> thePercentages) {
        percentages = thePercentages;
        return this;
    }

    public SalarySpecificationBuilder withPlan(final CareerPlan plan) {
        careerPlan = plan;
        return this;
    }

    public SalarySpecificationBuilder withYear(final int theYear) {
        year = theYear;
        return this;
    }

    public SalarySpecificationBuilder withLevel(final CareerPlanLevel level) {
        careerPlanLevel = level;
        return this;
    }

    public SalarySpecification build() {
        return new SalarySpecification(year, careerPlan, careerPlanLevel, minSalary, maxSalary, percentages);
    }
}
