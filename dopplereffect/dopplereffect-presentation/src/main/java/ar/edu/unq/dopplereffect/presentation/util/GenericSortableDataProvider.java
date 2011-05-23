/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package ar.edu.unq.dopplereffect.presentation.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * implementation of IDataProvider for contacts that keeps track of sort
 * information
 * 
 * @author igor
 * 
 */
public class GenericSortableDataProvider<T> extends SortableDataProvider<T> {
    private static final long serialVersionUID = 1L;

    private List<T> list;
	private SortableDataProviderComparator comparator = new SortableDataProviderComparator();


    /**
     * constructor
     */
    public GenericSortableDataProvider(final List<T> results, String sortName) {
        this.list = results;
        this.setSort(sortName, true);
    }

    /**
     * Subclass to lazy load the list
     * 
     * @return The list
     */
    protected List<T> getData() {
        return list;
    }

    /**
     * @see IDataProvider#iterator(int, int)
     */
    public Iterator<? extends T> iterator(final int first, final int count) {
        List<T> list = this.getData();

        int toIndex = first + count;
        if (toIndex > list.size()) {
            toIndex = list.size();
        }
		Collections.sort(list, comparator);

        return list.subList(first, toIndex).listIterator();
    }

    /**
     * @see IDataProvider#size()
     */
    public int size() {
        return this.getData().size();
    }

    /**
     * @see IDataProvider#model(Object)
     */
    public IModel<T> model(final T object) {
        return new Model((Serializable) object);
    }

    /**
     * @see org.apache.wicket.model.IDetachable#detach()
     */
    @Override
    public void detach() {
    }
    
	class SortableDataProviderComparator implements Comparator<T>, Serializable {
		public int compare(final T o1, final T o2) {
			PropertyModel<Comparable> model1 = new PropertyModel<Comparable>(o1, getSort().getProperty());
			PropertyModel<Comparable> model2 = new PropertyModel<Comparable>(o2, getSort().getProperty());

			int result = model1.getObject().compareTo(model2.getObject());

			if (!getSort().isAscending()) {
				result = -result;
			}

			return result;
		}

	}

}
