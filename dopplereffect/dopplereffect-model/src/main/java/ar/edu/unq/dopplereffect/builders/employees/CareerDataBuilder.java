package ar.edu.unq.dopplereffect.builders.employees;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.builders.Builder;
import ar.edu.unq.dopplereffect.employees.CareerData;
import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;

public class CareerDataBuilder implements Builder<CareerData> {

    protected transient DateTime joinDate = new DateTime();

    protected transient CareerPlan careerPlan = CareerPlan.TESTER;

    protected transient CareerPlanLevel level = new CareerPlanLevelBuilder().build();

    protected transient int percentage = 0;

    public CareerDataBuilder withJoinDate(final DateTime theJoinDate) {
        joinDate = theJoinDate;
        return this;
    }

    public CareerDataBuilder withCareerPlan(final CareerPlan theCareerPlan) {
        careerPlan = theCareerPlan;
        return this;
    }

    public CareerDataBuilder withCareerPlanLevelName(final String careerPlanLevelName) {
        level.setName(careerPlanLevelName);
        return this;
    }

    public CareerDataBuilder withPercentage(final int thePercentage) {
        percentage = thePercentage;
        return this;
    }

    @Override
    public CareerData build() {
        return new CareerData(joinDate, careerPlan, level, percentage);
    }

}
