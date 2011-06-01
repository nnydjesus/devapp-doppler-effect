package ar.edu.unq.dopplereffect.service;

import java.io.Serializable;
import java.util.List;

import ar.edu.unq.dopplereffect.repositories.Repository;

@org.springframework.stereotype.Service
public class ServiceImpl<T> implements Service<T>, Serializable {

    private static final long serialVersionUID = -806879556462184789L;

    private Repository<T> repository;
    
    public ServiceImpl() {
    	super();
	}

    @Override
    public void save(final T stock) {
        this.getRepository().save(stock);
    }

    @Override
    public void update(final T stock) {
        this.getRepository().update(stock);
    }

    @Override
    public void delete(final T stock) {
        this.getRepository().delete(stock);
    }

    @Override
    public T getByName(final String name) {
        return this.getRepository().getByName(name);
    }

    @Override
    public List<T> searchAll() {
        return this.getRepository().searchAll();
    }

    public void setRepository(final Repository<T> repository) {
        this.repository = repository;
    }

    public Repository<T> getRepository() {
        return repository;
    }

    @Override
    public List<T> searchByExample(final T object) {
        return repository.searchByExample(object);
    }
}