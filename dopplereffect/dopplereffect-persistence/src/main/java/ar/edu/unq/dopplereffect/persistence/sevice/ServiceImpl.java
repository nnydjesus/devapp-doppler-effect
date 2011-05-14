package ar.edu.unq.dopplereffect.persistence.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unq.dopplereffect.repository.Repository;
import ar.edu.unq.dopplereffect.service.Service;

@org.springframework.stereotype.Service("service")
public class ServiceImpl<T> implements Service<T> {

    @Autowired
    private Repository<T> repository;

    public void setStockDao(final Repository<T> stockDao) {
        this.setRepository(stockDao);
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

    private void setRepository(final Repository<T> repository) {
        this.repository = repository;
    }

    private Repository<T> getRepository() {
        return repository;
    }
}