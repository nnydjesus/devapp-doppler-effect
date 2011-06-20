package ar.edu.unq.dopplereffect.persistence.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.dopplereffect.persistence.util.CustomHibernateRepositorySupport;
import ar.edu.unq.dopplereffect.repositories.Repository;

public class HibernatePersistentRepository<T> extends CustomHibernateRepositorySupport implements Repository<T> {

    private static final long serialVersionUID = 1L;

    // es cualquiera esto, pero sino chilla el PMD
    private static final String UNCHECKED = "unchecked";

    /* ************************ INSTANCE VARIABLES ************************ */

    private Class<T> entityClass;

    /* *************************** CONSTRUCTORS *************************** */

    @SuppressWarnings(UNCHECKED)
    public HibernatePersistentRepository() {
        this((Class<T>) Object.class);
    }

    public HibernatePersistentRepository(final Class<T> clazz) {
        super();
        this.setEntityClass(clazz);
    }

    /* **************************** ACCESSORS ***************************** */

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    public void save(final T object) {
        this.getHibernateTemplate().save(object);
    }

    public void saveAll(final T... objects) {
        for (T object : objects) {
            this.getHibernateTemplate().save(object);
        }
    }

    @Override
    public void update(final T object) {
        this.getHibernateTemplate().update(object);
    }

    @Override
    public void delete(final T object) {
        this.getHibernateTemplate().delete(object);
    }

    @Override
    @SuppressWarnings(UNCHECKED)
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

    @Override
    @SuppressWarnings(UNCHECKED)
    public List<T> searchByExample(final T object) {
        return this.getHibernateTemplate().findByExample(object);
    }

    @Override
    public List<T> searchByName(final String name) {
        return this.searchByName("name", name);
    }

    public List<T> searchByName(final String field, final String name) {
        return this.getByCriterionList(Restrictions.like(field, "%" + name + "%"));
    }

    protected T getByCriterion(final Criterion criterion) {
        List<T> criterionList = this.getByCriterionList(criterion);
        if (criterionList.isEmpty()) {
            return null;
        } else {
            return criterionList.get(0);
        }
    }

    @SuppressWarnings(UNCHECKED)
    protected List<T> getByCriterionList(final Criterion criterion) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(criterion);
        return criteria.list();
    }
}
