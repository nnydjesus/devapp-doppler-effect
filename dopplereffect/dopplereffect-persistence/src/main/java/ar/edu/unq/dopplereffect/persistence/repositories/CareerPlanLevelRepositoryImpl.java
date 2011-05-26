package ar.edu.unq.dopplereffect.persistence.repositories;

import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;

public class CareerPlanLevelRepositoryImpl extends HibernatePersistentRepository<CareerPlanLevel> {

    public CareerPlanLevelRepositoryImpl() {
        super(CareerPlanLevel.class);
    }

}
