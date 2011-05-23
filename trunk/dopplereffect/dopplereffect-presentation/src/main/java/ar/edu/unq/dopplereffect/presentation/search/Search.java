package ar.edu.unq.dopplereffect.presentation.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Search<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Collection<T> results = new ArrayList<T>();

    public abstract void save(T t);

    public Collection<T> getResults() {
        return results;
    }

    public void setResults(final Collection<T> resultado) {
        this.results = resultado;
    }

    public abstract void search();

    public abstract void remove(T t);

    public abstract void update(T t);

    public abstract T createExample();

}
