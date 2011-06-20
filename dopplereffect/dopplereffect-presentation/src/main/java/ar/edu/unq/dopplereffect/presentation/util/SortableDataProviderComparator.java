package ar.edu.unq.dopplereffect.presentation.util;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.wicket.model.PropertyModel;

public class SortableDataProviderComparator<T> implements Comparator<T>, Serializable {

    private static final long serialVersionUID = 1L;

    private GenericSortableDataProvider<T> genericSortableDataProvider;

    SortableDataProviderComparator(final GenericSortableDataProvider<T> genericSortableDataProvider) {
        this.genericSortableDataProvider = genericSortableDataProvider;
    }

    public GenericSortableDataProvider<T> getGenericSortableDataProvider() {
        return genericSortableDataProvider;
    }

    public void setGenericSortableDataProvider(final GenericSortableDataProvider<T> genericSortableDataProvider) {
        this.genericSortableDataProvider = genericSortableDataProvider;
    }

    @Override
    public int compare(final T o1, final T o2) {
        PropertyModel<Comparable<Object>> model1 = new PropertyModel<Comparable<Object>>(o1,
                genericSortableDataProvider.getSort().getProperty());
        PropertyModel<Comparable<Object>> model2 = new PropertyModel<Comparable<Object>>(o2,
                genericSortableDataProvider.getSort().getProperty());

        int result = model1.getObject().compareTo(model2.getObject());

        if (!genericSortableDataProvider.getSort().isAscending()) {
            result = -result;
        }

        return result;
    }

}