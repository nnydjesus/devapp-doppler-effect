package ar.edu.unq.dopplereffect.presentation.search;

import ar.edu.unq.tpi.util.common.ReflectionUtils;

public abstract class SearchByExampleModel<T> extends SearchModel<T> {

    private static final long serialVersionUID = 6216820887576813914L;

    private T example;

    public SearchByExampleModel(final Class<T> entityClass) {
        super(entityClass);
        example = this.createExample();
    }

    public T createExample() {
        return ReflectionUtils.instanciate(this.getEntityType());
    }

    public T getExample() {
        return example;
    }

    protected void setExample(final T example) {
        this.example = example;
    }

    @Override
    public void search() {
        this.searchByExample();
    }

    public void searchByExample() {
        this.setResults(this.getService().searchByExample(this.getExample()));
    }
}
