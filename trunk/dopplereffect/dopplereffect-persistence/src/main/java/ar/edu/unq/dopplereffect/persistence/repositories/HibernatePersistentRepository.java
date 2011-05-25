package ar.edu.unq.dopplereffect.persistence.repositories;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.dopplereffect.persistence.util.CustomHibernateRepositorySupport;
import ar.edu.unq.dopplereffect.project.Project;
import ar.edu.unq.dopplereffect.repositories.Repository;

public class HibernatePersistentRepository<T> extends CustomHibernateRepositorySupport implements Repository<T> {

    private Class<T> entityClass;

    public HibernatePersistentRepository(final Class<T> clazz) {
        this.setEntityClass(clazz);
    }

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

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<T> searchAll() {
        return this.getHibernateTemplate().loadAll(this.getEntityClass());
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getByName(final String name) {
        DetachedCriteria detachedCriteria = this.createCriteria();
        detachedCriteria.add(Restrictions.like("name", name));
        List<Project> list = this.getHibernateTemplate().findByCriteria(detachedCriteria);

        if (list.isEmpty()) {
            return null;
        }
        return (T) list.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> searchByExample(final T object) {
        return this.getHibernateTemplate().findByExample(object);
    }

    protected DetachedCriteria createCriteria() {
        DetachedCriteria detachedCriteria = new DetachedCriteria(this.getEntityClass().getSimpleName()) {
            private static final long serialVersionUID = 1L;
        };
        return detachedCriteria;
    }

    public void setEntityClass(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }
}
