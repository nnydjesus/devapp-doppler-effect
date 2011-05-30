package ar.edu.unq.dopplereffect.presentation.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.dopplereffect.service.Service;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

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
        // this.setResultado(entityHome.getInstance().buscarByExample(this.crearExample()));
    }

    public void remove(final T entity) {
        this.getService().delete(entity);
        // entityHome.getInstance().eliminar(entity);
        this.getResults().remove(entity);
    }

    public void update(final T entity) {
        this.getService().update(entity);
        // entityHome.getInstance().actualizar(entity);
        // this.remove(entity);
        // this.save(entity);
        this.results.remove(entity);
        this.results.add(entity);
    }

    public T createExample() {
        return ReflectionUtils.instanciate(this.getEntityType());
    }

    public Class<T> getEntityType() {
        return entityType;
    }

    public void setEntityType(final Class<T> entityType) {
        this.entityType = entityType;
    }

    public abstract void setService(Service<T> service);

    public abstract Service<T> getService();

}
