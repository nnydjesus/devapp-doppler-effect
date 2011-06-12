package ar.edu.unq.dopplereffect.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.dopplereffect.repositories.Repository;

public class ServiceImpl<T> implements PersistenceService<T>, Serializable {

    private static final long serialVersionUID = -806879556462184789L;

    private Repository<T> repository;

    @Override
    @Transactional
    public void save(final T stock) {
        this.getRepository().save(stock);
    }

    @Override
    @Transactional
    public void update(final T stock) {
        this.getRepository().update(stock);
    }

    @Override
    @Transactional
    public void delete(final T stock) {
        this.getRepository().delete(stock);
    }

    @Override
    @Transactional
    public T getByName(final String name) {
        return this.getRepository().getByName(name);
    }

    @Override
    public List<T> searchAll() {
        return this.getRepository().searchAll();
    }

    @Override
    @Transactional
    public List<T> searchByExample(final T object) {
        return repository.searchByExample(object);
    }

    public void setRepository(final Repository<T> repository) {
        this.repository = repository;
    }

    public Repository<T> getRepository() {
        return repository;
    }

}