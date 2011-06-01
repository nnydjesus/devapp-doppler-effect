package ar.edu.unq.dopplereffect.persistence.repositories;

import ar.edu.unq.dopplereffect.employees.CareerData;

public class CareerDataRepositoryImpl extends HibernatePersistentRepository<CareerData> {

    private static final long serialVersionUID = 1L;

    public CareerDataRepositoryImpl() {
        super(CareerData.class);
    }
}
