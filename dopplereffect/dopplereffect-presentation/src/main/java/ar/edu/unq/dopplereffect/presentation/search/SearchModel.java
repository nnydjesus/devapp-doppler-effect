package ar.edu.unq.dopplereffect.presentation.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ar.edu.unq.dopplereffect.service.DTO;

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

    public <D extends DTO> void save(final D entity) {
        this.callSaveOnService(entity);
        this.search();
    }

    public void search() {
        this.setResults(this.getAllResultsFromService());
    }
    
    public void searchByName(String name) {
        this.setResults(this.getByNameResultsFromService(name));
    }

    public void remove(final T entity) {
        this.callRemoveOnService(entity);
        this.search();
    }

    public <D extends DTO> void update(final D entity) {
        this.callUpdateOnService(entity);
        this.search();
    }

    public Class<T> getEntityType() {
        return entityType;
    }

    public void setEntityType(final Class<T> entityType) {
        this.entityType = entityType;
    }

    public void reset() {
        this.setResults(new LinkedList<T>());
    }

    protected abstract List<T> getAllResultsFromService();
    
    protected abstract List<T> getByNameResultsFromService(String name);

    protected abstract <D extends DTO> void callSaveOnService(D entity);

    protected abstract void callRemoveOnService(T entity);

    protected abstract <D extends DTO> void callUpdateOnService(D entity);

    public abstract <D extends DTO> D createEditDTO(T viewDTO);
}
