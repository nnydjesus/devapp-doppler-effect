package ar.edu.unq.dopplereffect.persistence.repositories;

import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;

public class CareerPlanLevelRepositoryImpl extends
		HibernatePersistentRepository<CareerPlanLevel> {

	private static final long serialVersionUID = 1L;

	public CareerPlanLevelRepositoryImpl() {
		super(CareerPlanLevel.class);
	}

}
