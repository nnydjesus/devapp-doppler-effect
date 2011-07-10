package ar.edu.unq.dopplereffect.presentation.util;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import ar.edu.unq.tpi.util.common.ReflectionUtils;
import ar.edu.unq.tpi.util.commons.exeption.UserException;

import com.wiquery.plugins.jqgrid.model.SortInfo;
import com.wiquery.plugins.jqgrid.model.SortOrder;

class IOrderComparator<T> implements Comparator<T>, Serializable {

    private static final long serialVersionUID = -1519486578091464384L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private SortInfo order;

    /* *************************** CONSTRUCTORS *************************** */

    public IOrderComparator(final SortInfo order) {
        this.setOrder(order);
    }

    /* **************************** ACCESSORS ***************************** */

    public SortInfo getOrder() {
        return order;
    }

    public void setOrder(final SortInfo order) {
        this.order = order;
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    @SuppressWarnings("unchecked")
    public int compare(final T o1, final T o2) {
        try {
            Object ov1 = ReflectionUtils
                    .invokeMethod(o1, "get" + StringUtils.capitalize(this.getOrder().getProperty()));
            Object ov2 = ReflectionUtils
                    .invokeMethod(o2, "get" + StringUtils.capitalize(this.getOrder().getProperty()));
            if (ov1 instanceof Comparable && ov2 instanceof Comparable) {
                Comparable<Object> c1 = (Comparable<Object>) ov1;
                Comparable<Object> c2 = (Comparable<Object>) ov2;
                if (this.getOrder().getSortOrder().equals(SortOrder.asc)) {
                    return c1.compareTo(c2);
                } else if (this.getOrder().getSortOrder().equals(SortOrder.desc)) {
                    return c2.compareTo(c1);
                }
            }
        } catch (Exception e) {
            throw new UserException(e);
        }
        return 0;
    }
}