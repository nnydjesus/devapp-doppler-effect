package ar.edu.unq.dopplereffect.persistence.employee;

import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.persistence.repositories.HibernatePersistentRepository;

public class CareerPlanLevelRepositoryImpl extends HibernatePersistentRepository<CareerPlanLevel> {

    private static final long serialVersionUID = 1L;

    public CareerPlanLevelRepositoryImpl() {
        super(CareerPlanLevel.class);
    }

}
