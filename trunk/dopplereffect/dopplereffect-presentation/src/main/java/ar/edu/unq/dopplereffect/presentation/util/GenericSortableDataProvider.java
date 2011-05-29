package ar.edu.unq.dopplereffect.presentation.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 * 
 */
public class GenericSortableDataProvider<T extends Serializable> extends SortableDataProvider<T> implements
        Serializable {
    private static final long serialVersionUID = 1L;

    private IModel listModel;

    private String id;

    private SortableDataProviderComparator comparator = new SortableDataProviderComparator();

    /**
     * constructor
     */
    public GenericSortableDataProvider(final String id, final Object model, final String sortName) {
        super();
        // this.setList(results);
        this.id = id;
        this.setListModel(new CompoundPropertyModel(model));
        this.setSort(sortName, true);
    }

    /**
     * Subclass to lazy load the list
     * 
     * @return The list
     */
    protected List<T> getData() {
        return (List<T>) ReflectionUtils.invokeMethod(this.getListModel().getObject(),
                "get" + StringUtils.capitalize(id));
    }

    /**
     * @see IDataProvider#iterator(int, int)
     */
    @Override
    public Iterator<? extends T> iterator(final int first, final int count) {
        List<T> aList = this.getData();

        int toIndex = first + count;
        if (toIndex > aList.size()) {
            toIndex = aList.size();
        }
        Collections.sort(aList, this.getComparator());

        return aList.subList(first, toIndex).listIterator();
    }

    /**
     * @see IDataProvider#size()
     */
    @Override
    public int size() {
        return this.getData().size();
    }

    /**
     * @see IDataProvider#model(Object)
     */
    @Override
    public IModel<T> model(final T object) {
        return new Model<T>(object);
    }

    // public void setList(final List<T> list) {
    // this.list = list;
    // }
    //
    // public List<T> getList() {
    // return list;
    // }

    public void setComparator(final SortableDataProviderComparator aComparator) {
        this.comparator = aComparator;
    }

    public SortableDataProviderComparator getComparator() {
        return comparator;
    }

    public void setListModel(final IModel listModel) {
        this.listModel = listModel;
    }

    public IModel getListModel() {
        return listModel;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    class SortableDataProviderComparator implements Comparator<T>, Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(final T o1, final T o2) {
            PropertyModel<Comparable<Object>> model1 = new PropertyModel<Comparable<Object>>(o1,
                    GenericSortableDataProvider.this.getSort().getProperty());
            PropertyModel<Comparable<Object>> model2 = new PropertyModel<Comparable<Object>>(o2,
                    GenericSortableDataProvider.this.getSort().getProperty());

            int result = model1.getObject().compareTo(model2.getObject());

            if (!GenericSortableDataProvider.this.getSort().isAscending()) {
                result = -result;
            }

            return result;
        }

    }

}
