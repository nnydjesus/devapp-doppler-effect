package ar.edu.unq.dopplereffect.persistence.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.dopplereffect.persistence.util.CustomHibernateRepositorySupport;
import ar.edu.unq.dopplereffect.repositories.Repository;

public class HibernatePersistentRepository<T> extends CustomHibernateRepositorySupport implements Repository<T> {
    private static final long serialVersionUID = 1L;

    private Class<T> entityClass;

    public HibernatePersistentRepository(final Class<T> clazz) {
        super();
        this.setEntityClass(clazz);
    }

    @Override
    @Transactional
    public void save(final T object) {
        this.getHibernateTemplate().save(object);
    }

    @Transactional
    public void saveAll(final T... objects) {
        for (T object : objects) {
            this.getHibernateTemplate().save(object);
        }
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

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<T> searchAll() {
        return this.getHibernateTemplate().loadAll(this.getEntityClass());
    }

    @Override
    public T getByName(final String name) {
        return this.getByCriterion(Restrictions.eq("name", name));
    }

    @Override
    public T getByLikeName(final String name) {
        return this.getByCriterion(Restrictions.like("name", "%" + name + "%"));
    }

    @SuppressWarnings("unchecked")
    private T getByCriterion(final Criterion criterion) {
        Criteria criteria = this.getSession().createCriteria(this.entityClass);
        criteria.add(criterion);
        List<T> results = criteria.list();
        if (results.isEmpty()) {
            return null;
        } else {
            return (T) criteria.list().get(0);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> searchByExample(final T object) {
        return this.getHibernateTemplate().findByExample(object);
    }

    public void setEntityClass(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }
}
