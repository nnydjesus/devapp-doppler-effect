package ar.edu.unq.dopplereffect.presentation.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 * 
 */
public class GenericSortableDataProvider<T> extends SortableDataProvider<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private IModel<T> listModel;

    private String id;

    // private SingleSortState state = new SingleSortState();;

    private SortableDataProviderComparator<T> comparator = new SortableDataProviderComparator<T>(this);

    /**
     * constructor
     */
    public GenericSortableDataProvider(final String id, final Object model) {
        super();
        // this.setList(results);
        this.id = id;
        this.setListModel(new CompoundPropertyModel<T>(model));
    }

    public GenericSortableDataProvider(final String id, final Object model, final String sortName) {
        this(id, model);
        this.setSort(sortName, true);
    }

    /**
     * Subclass to lazy load the list
     * 
     * @return The list
     */
    @SuppressWarnings("unchecked")
    protected Collection<T> getData() {
        return (Collection<T>) ReflectionUtils.invokeMethod(this.getListModel().getObject(),
                "get" + StringUtils.capitalize(id));
    }

    public Iterator<? extends T> simpleInterator(final int first, final int count) {
        Collection<T> aList = this.getData();

        int toIndex = first + count;
        if (toIndex > aList.size()) {
            toIndex = aList.size();
        }
        // Collections.sort(aList, this.getComparator());

        return aList.iterator();
        // return aList.subList(first, toIndex).listIterator();
    }

    @Override
    public Iterator<? extends T> iterator(final int first, final int count) {
        // List<T> aList = this.getData();
        // if (this.getSortState() == null || this.getSort() == null) {
        return this.simpleInterator(first, count);
        // } else {
        // SortParam sort = this.getSort();
        // SortInfo sortInfo = new SortInfo(sort.getProperty(),
        // sort.isAscending() ? SortOrder.asc : SortOrder.desc);
        // if (sortInfo != null) {
        // QueryUtils.sortList(aList, sortInfo);
        // }
        // int toIndex = first + count;
        // if (toIndex > aList.size()) {
        // toIndex = aList.size();
        // }
        // return aList.subList(first, toIndex).listIterator();
        // }
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

    public SortableDataProviderComparator<T> getComparator() {
        return comparator;
    }

    public void setComparator(final SortableDataProviderComparator<T> aComparator) {
        this.comparator = aComparator;
    }

    public IModel<T> getListModel() {
        return listModel;
    }

    public void setListModel(final IModel<T> listModel) {
        this.listModel = listModel;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }
}
