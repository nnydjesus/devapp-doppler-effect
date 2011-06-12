package ar.edu.unq.dopplereffect.service.salaryspec;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.service.DTO;

public class SalarySpecDTO implements DTO {

    private static final long serialVersionUID = 3411660773006513822L;

    private int year;

    private float minSalary;

    private float maxSalary;

    private CareerPlan careerPlan;

    private String careerPlanLevel;

    private List<Integer> percentages;

    public SalarySpecDTO() {
        percentages = new LinkedList<Integer>();
    }

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

    public CareerPlan getCareerPlan() {
        return careerPlan;
    }

    public void setCareerPlan(final CareerPlan careerPlan) {
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

    public String getViewPercentages() {
        String result = "";
        for (int perc : this.getPercentages()) {
            result += perc + ",";
        }
        if (!this.getPercentages().isEmpty()) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}
