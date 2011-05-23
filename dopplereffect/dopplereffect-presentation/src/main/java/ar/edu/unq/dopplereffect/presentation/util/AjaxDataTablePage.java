package ar.edu.unq.dopplereffect.presentation.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.edu.unq.dopplereffect.presentation.component.AbstractWebPage;
import ar.edu.unq.dopplereffect.presentation.pages.ActionPanel;
import ar.edu.unq.dopplereffect.presentation.pages.AjaxActionPanel;
import ar.edu.unq.dopplereffect.presentation.search.Search;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 */
public class AjaxDataTablePage<T, B extends WebPage> implements Serializable{

	private static final long serialVersionUID = 1L;

	private Component resultSection;

    private B parentPage;

    private Search<T> search;

    private List<String> fields;

    private Class<? extends WebPage> abmClass;

    private AjaxFallbackDefaultDataTable<T> ajaxdataTable;

    public AjaxDataTablePage(final String id, final String sortName,  final Search<T> search, final List<String> fields,
            final Class<? extends WebPage> abm) {

        this.search = search;
        this.setFields(fields);
        this.abmClass = abm;

        ArrayList<IColumn<T>> columns = new ArrayList<IColumn<T>>();

        for (String field : fields) {
            columns.add(new PropertyColumn<T>(new Model<String>(StringUtils.capitalize(field)), field));
        }
        
        columns.add(new AbstractColumn<T>(new Model<String>("Edit")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId,
                    final IModel<T> model) {
                // cellItem.add(new Label(componentId, new Model("")));
                cellItem.add(new ActionPanel(componentId) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onAction() {
                    	WebPage page = ReflectionUtils.instanciate(abmClass,
                                AjaxDataTablePage.this.getParentPage(), model.getObject(), true);
                        this.setResponsePage(page);
                    }

                });
            }
        });

        columns.add(new AbstractColumn<T>(new Model<String>("Delete")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId,
                    final IModel<T> rowModel) {
                cellItem.add(new AjaxActionPanel(componentId) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onAction(final AjaxRequestTarget target) {
                        AjaxDataTablePage.this.getBuscador().remove(rowModel.getObject());
                        target.addComponent(AjaxDataTablePage.this.ajaxdataTable);
                    }
                });
            }
        });

        this.ajaxdataTable = new AjaxFallbackDefaultDataTable<T>(id, columns, new GenericSortableDataProvider<T>(this
                .getBuscador().getResults(), sortName), Search.PAGE_SIZE);
    }

    public Component getResultSection() {
        return resultSection;
    }

    public void setResultSection(final Component resultSection) {
        this.resultSection = resultSection;
    }

    public B getParentPage() {
        return parentPage;
    }

    public void setParentPage(final B page) {
        this.parentPage = page;
    }

    public Search<T> getBuscador() {
        return search;
    }

    public void setSearch(final Search<T> search) {
        this.search = search;
    }

    public AjaxFallbackDefaultDataTable<T> getAjaxdataTable() {
        return ajaxdataTable;
    }

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public List<String> getFields() {
		return fields;
	}

}
