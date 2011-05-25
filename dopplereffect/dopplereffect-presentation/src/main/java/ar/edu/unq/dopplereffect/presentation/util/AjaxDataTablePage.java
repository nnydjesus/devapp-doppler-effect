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
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.edu.unq.dopplereffect.presentation.panel.ActionPanel;
import ar.edu.unq.dopplereffect.presentation.panel.AjaxActionPanel;
import ar.edu.unq.dopplereffect.presentation.search.Search;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 */
public class AjaxDataTablePage<T extends Serializable, B extends Component> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Component resultSection;

    private B parentPage;

    private Search<T> search;

    private List<String> fields;

    private Class<? extends Component> abmClass;

    private AjaxFallbackDefaultDataTable<T> ajaxdataTable;

    public AjaxDataTablePage(final String id, final String sortName, final Search<T> aSearch,
            final CallBack<Component> aCallBack, final List<String> fields, final Class<? extends Component> abm) {

        this.setFields(fields);
        this.setAbmClass(abm);
        this.setSearch(aSearch);

        final CallBack<Component> callBack = aCallBack;
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
                        Component page = ReflectionUtils.instanciate(AjaxDataTablePage.this.getAbmClass(), "body",
                                AjaxDataTablePage.this.getParentPage(), model.getObject(), true);
                        callBack.execute(page);
                        // this.setResponsePage(page);
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
                        target.add(AjaxDataTablePage.this.getAjaxdataTable());
                    }
                });
            }
        });

        this.setAjaxdataTable(new AjaxFallbackDefaultDataTable<T>(id, columns, new GenericSortableDataProvider<T>(this
                .getBuscador().getResults(), sortName), Search.PAGE_SIZE));
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

    public void setFields(final List<String> fields) {
        this.fields = fields;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setAjaxdataTable(final AjaxFallbackDefaultDataTable<T> ajaxdataTable) {
        this.ajaxdataTable = ajaxdataTable;
    }

    public void setAbmClass(final Class<? extends Component> abmClass) {
        this.abmClass = abmClass;
    }

    public Class<? extends Component> getAbmClass() {
        return abmClass;
    }

}