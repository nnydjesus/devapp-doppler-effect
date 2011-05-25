package ar.edu.unq.dopplereffect.presentation.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.tpi.util.common.ReflectionUtils;

public abstract class Search<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int PAGE_SIZE = 4;

    private List<T> results = new ArrayList<T>();

    private Class<T> entityType;

    public Search(final Class<T> entityClass) {
        this.entityType = entityClass;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(final List<T> resultado) {
        this.results = resultado;
    }

    public void save(final T entity) {
        // entityHome.getInstance().agregar(entity);
        this.getResults().add(entity);
        this.search();
    }

    public void search() {
        // this.setResultado(entityHome.getInstance().buscarByExample(this.crearExample()));
    }

    public void remove(final T entity) {
        // entityHome.getInstance().eliminar(entity);
        this.getResults().remove(entity);
    }

    public void update(final T entity) {
        // entityHome.getInstance().actualizar(entity);
        this.remove(entity);
        this.save(entity);
        this.search();
    }

    public T createExample() {
        return ReflectionUtils.instanciate(entityType);
    }

    public Class<T> getEntityType() {
        return entityType;
    }

}
