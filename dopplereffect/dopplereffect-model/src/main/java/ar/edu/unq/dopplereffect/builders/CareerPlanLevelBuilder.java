package ar.edu.unq.dopplereffect.builders;

import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;

public class CareerPlanLevelBuilder implements Builder<CareerPlanLevel> {

    protected transient String name;

    public CareerPlanLevelBuilder withName(final String theName) {
        name = theName;
        return this;
    }

    @Override
    public CareerPlanLevel build() {
        return new CareerPlanLevel(name);
    }
}
