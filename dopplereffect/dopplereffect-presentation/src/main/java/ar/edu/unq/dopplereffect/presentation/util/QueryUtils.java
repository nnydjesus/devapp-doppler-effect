package ar.edu.unq.dopplereffect.presentation.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.edu.unq.tpi.util.commons.exeption.UserException;

import com.wiquery.plugins.jqgrid.model.SortInfo;
import com.wiquery.plugins.jqgrid.model.SortOrder;
import com.wiquery.plugins.jqgrid.util.ReflectionUtils;

/**
 * 
 */
public class QueryUtils {

    private class IOrderComparator<T> implements Comparator<T> {

        private SortInfo order;

        public IOrderComparator(final SortInfo order) {
            this.setOrder(order);
        }

        @Override
        @SuppressWarnings("unchecked")
        public int compare(final T o1, final T o2) {
            try {
                Object ov1 = ReflectionUtils.getPropertyValue(o1, this.getOrder().getProperty());
                Object ov2 = ReflectionUtils.getPropertyValue(o2, this.getOrder().getProperty());
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
                new UserException(e);
            }
            return 0;
        }

        public void setOrder(final SortInfo order) {
            this.order = order;
        }

        public SortInfo getOrder() {
            return order;
        }
    }

    public <T> T matchQuery(final T bean, final T searchBean, final String... searchFields) {
        if (bean == null) {
            return null;
        }
        if (searchFields == null) {
            return bean;
        }
        for (String filter : searchFields) {
            String propertyName = filter;
            try {
                Object rValue = ReflectionUtils.getPropertyValue(searchBean, propertyName);
                if (rValue != null) {
                    Object value = this.getPropertyValue(bean, propertyName);
                    String strVale = value.toString();
                    if (!strVale.startsWith(rValue.toString())) {
                        return null;
                    }
                }
            } catch (Exception e) {
                return null;
            }
        }
        return bean;
    }

    public Object getPropertyValue(final Object bean, final String propertyPath) throws NoSuchFieldException {
        if (bean == null) {
            throw new IllegalArgumentException("bean cannot be null");
        }
        Field field = ReflectionUtils.getField(bean.getClass(), propertyPath);
        field.setAccessible(true);
        try {
            return field.get(bean);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public <T> List<T> sortList(final List<T> list, final SortInfo info) {
        IOrderComparator<T> comparator = null;
        if (info != null) {
            comparator = new IOrderComparator<T>(info);
        }
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
        return list;
    }

    public <T> List<T> findSubList(final List<T> list, final T searchBean, final SortInfo info,
            final String... searchFields) {
        if (list == null) {
            return null;
        }
        List<T> nlist = new ArrayList<T>();
        for (T bean : list) {
            T result = this.matchQuery(bean, searchBean, searchFields);
            if (result != null) {
                nlist.add(bean);
            }
        }
        return this.sortList(nlist, info);
    }
}
