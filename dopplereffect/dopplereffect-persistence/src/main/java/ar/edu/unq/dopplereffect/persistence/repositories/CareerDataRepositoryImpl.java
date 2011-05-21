package ar.edu.unq.dopplereffect.persistence.repositories;

import ar.edu.unq.dopplereffect.employees.CareerData;
import ar.edu.unq.dopplereffect.repositories.Repository;

@org.springframework.stereotype.Repository
public class CareerDataRepositoryImpl extends HibernatePersistentRepository<CareerData> implements
        Repository<CareerData> {
}
