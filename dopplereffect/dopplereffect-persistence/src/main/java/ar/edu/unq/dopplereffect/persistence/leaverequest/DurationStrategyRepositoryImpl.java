package ar.edu.unq.dopplereffect.persistence.leaverequest;

import ar.edu.unq.dopplereffect.persistence.HibernatePersistentRepository;
import ar.edu.unq.dopplereffect.time.DurationStrategy;

public class DurationStrategyRepositoryImpl extends HibernatePersistentRepository<DurationStrategy> {

    private static final long serialVersionUID = 8356707238183454593L;

    public DurationStrategyRepositoryImpl() {
        super(DurationStrategy.class);
    }
}
