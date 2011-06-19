package ar.edu.unq.dopplereffect.presentation.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.wiquery.plugins.jqgrid.model.SortInfo;
import com.wiquery.plugins.jqgrid.util.ReflectionUtils;

/**
 * Clase que ordena.. estaba en el ejemplo de wiqueryplugins
 * 
 */
public class QueryUtils implements Serializable{
	private static final long serialVersionUID = 1L;

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
