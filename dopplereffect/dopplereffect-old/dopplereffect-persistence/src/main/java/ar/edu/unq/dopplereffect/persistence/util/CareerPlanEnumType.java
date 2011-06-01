package ar.edu.unq.dopplereffect.persistence.util;

import ar.edu.unq.dopplereffect.employees.CareerPlan;

public class CareerPlanEnumType extends EnumUserType<CareerPlan> {
    public CareerPlanEnumType() {
        super(CareerPlan.class);
    }
}