package ar.edu.unq.dopplereffect.persistence.repositories;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.dopplereffect.repository.Repository;

@org.springframework.stereotype.Repository
public class PersistentRepository<T> extends HibernateDaoSupport implements Repository<T> {

    @Override
    @Transactional
    public void save(final T object) {
        this.getHibernateTemplate().save(object);
    }

    @Override
    @Transactional
    public void update(final T object) {
        this.getHibernateTemplate().update(object);
    }

    @Override
    @Transactional
    public void delete(final T object) {
        this.getHibernateTemplate().delete(object);
    }

    @Override
    @Transactional
    public List<T> searchAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getByName(final String name) {
        throw new UnsupportedOperationException();
    }
}