package ar.edu.unq.dopplereffect.service.salaryspec;

import java.util.List;

import ar.edu.unq.dopplereffect.service.DTO;

public class SalarySpecDTO implements DTO {

    private int year;

    private float minSalary;

    private float maxSalary;

    private String careerPlan;

    private String careerPlanLevel;

    private List<Integer> percentages;

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public float getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(final float maxSalary) {
        this.maxSalary = maxSalary;
    }

    public float getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(final float minSalary) {
        this.minSalary = minSalary;
    }

    public String getCareerPlan() {
        return careerPlan;
    }

    public void setCareerPlan(final String careerPlan) {
        this.careerPlan = careerPlan;
    }

    public String getCareerPlanLevel() {
        return careerPlanLevel;
    }

    public void setCareerPlanLevel(final String careerPlanLevel) {
        this.careerPlanLevel = careerPlanLevel;
    }

    public List<Integer> getPercentages() {
        return percentages;
    }

    public void setPercentages(final List<Integer> percentages) {
        this.percentages = percentages;
    }
}
