package ar.edu.unq.dopplereffect.presentation.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.dopplereffect.service.Service;

public abstract class SearchModel<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int PAGE_SIZE = 4;

    private List<T> results = new ArrayList<T>();

    private Class<T> entityType;

    public SearchModel(final Class<T> entityClass) {
        this.setEntityType(entityClass);
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(final List<T> resultado) {
        this.results = resultado;
    }

    public void save(final T entity) {
        this.getService().save(entity);
        this.getResults().add(entity);
        this.search();
    }

    public void search() {
        this.setResults(this.getService().searchAll());
    }

    public void remove(final T entity) {
        this.getService().delete(entity);
        this.getResults().remove(entity);
        this.search();
    }

    public void update(final T entity) {
        this.getService().update(entity);
        this.getResults().remove(entity);
        this.getResults().add(entity);
        this.search();
    }

    public Class<T> getEntityType() {
        return entityType;
    }

    public void setEntityType(final Class<T> entityType) {
        this.entityType = entityType;
    }

    public abstract Service<T> getService();

    public abstract void setService(Service<T> service);
}
