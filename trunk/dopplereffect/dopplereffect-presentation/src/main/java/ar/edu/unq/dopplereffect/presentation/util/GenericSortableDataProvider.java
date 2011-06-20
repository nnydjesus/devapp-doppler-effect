package ar.edu.unq.dopplereffect.presentation.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import ar.edu.unq.tpi.util.common.ReflectionUtils;

import com.wiquery.plugins.jqgrid.model.SortInfo;
import com.wiquery.plugins.jqgrid.model.SortOrder;

public class GenericSortableDataProvider<T> extends SortableDataProvider<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private IModel<T> listModel;

    private String id;

    private QueryUtils queryUtils = new QueryUtils();

    private SortableDataProviderComparator<T> comparator = new SortableDataProviderComparator<T>(this);

    /* *************************** CONSTRUCTORS *************************** */

    public GenericSortableDataProvider(final String id, final Object model, final String sortName) {
        super();
        this.id = id;
        this.setListModel(new CompoundPropertyModel<T>(model));
        this.setSort(sortName, true);
    }

    /* **************************** ACCESSORS ***************************** */

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

    public QueryUtils getQueryUtils() {
        return queryUtils;
    }

    public void setQueryUtils(final QueryUtils queryUtils) {
        this.queryUtils = queryUtils;
    }

    /* **************************** OPERATIONS **************************** */

    /**
     * Subclass to lazy load the list
     * 
     * @return The list
     */
    @SuppressWarnings("unchecked")
    protected List<T> getData() {
        return new ArrayList<T>((Collection<T>) ReflectionUtils.invokeMethod(this.getListModel().getObject(), "get"
                + StringUtils.capitalize(id)));
    }

    @Override
    public Iterator<? extends T> iterator(final int first, final int count) {
        List<T> aList = this.getData();
        SortParam sort = this.getSort();
        SortInfo sortInfo = new SortInfo(sort.getProperty(), sort.isAscending() ? SortOrder.asc : SortOrder.desc);
        this.getQueryUtils().sortList(aList, sortInfo);
        int toIndex = first + count;
        if (toIndex > aList.size()) {
            toIndex = aList.size();
        }
        return aList.subList(first, toIndex).listIterator();
    }

    @Override
    public int size() {
        return this.getData().size();
    }

    @Override
    public IModel<T> model(final T object) {
        return new Model<T>(object);
    }
}
