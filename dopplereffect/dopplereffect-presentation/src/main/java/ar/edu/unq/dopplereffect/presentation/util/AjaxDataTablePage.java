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
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.edu.unq.dopplereffect.presentation.panel.AjaxActionPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 */
public class AjaxDataTablePage<T extends Serializable> implements Serializable, ITable {

    private static final long serialVersionUID = 1L;

    private Component resultSection;

    private Component parentPage;

    private SearchModel<T> search;

    private List<String> fields;

    private Class<? extends Component> abmClass;

    private AjaxFallbackDefaultDataTable<T> ajaxdataTable;

    private WebMarkupContainer sortableAjaxWicket;

    private Panel parentPanel;

    private AjaxCallBack<Component> callBack;

    public AjaxDataTablePage(final Panel parent, final String id, final String sortName, final SearchModel<T> aSearch,
            final AjaxCallBack<Component> aCallBack, final List<String> fields, final Class<? extends Component> abm) {

        this.setParentPanel(parent);
        this.setFields(fields);
        this.setAbmClass(abm);
        this.setSearch(aSearch);

        callBack = aCallBack;
        ArrayList<IColumn<T>> columns = new ArrayList<IColumn<T>>();

        for (String field : fields) {
            columns.add(this.createPropertyColumn(field));
        }

        columns.add(new AbstractColumn<T>(new Model<String>("Edit")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId,
                    final IModel<T> model) {
                cellItem.add(new AjaxActionPanel(componentId, "edit.png") {
                    private static final long serialVersionUID = 1L;

                    @Override
                    @SuppressWarnings("synthetic-access")
                    public void onAction(final AjaxRequestTarget target) {
                        Component page = ReflectionUtils.instanciate(AjaxDataTablePage.this.getAbmClass(),
                                AjaxDataTablePage.this.getParentPanel().getId(),
                                AjaxDataTablePage.this.getParentPage(), model.getObject(), true);
                        callBack.execute(target, page);
                    }

                });
            }
        });

        columns.add(new AbstractColumn<T>(new Model<String>("Delete")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId,
                    final IModel<T> rowModel) {
                cellItem.add(new AjaxActionPanel(componentId, "delete.png") {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onAction(final AjaxRequestTarget target) {
                        AjaxDataTablePage.this.getSearch().remove(rowModel.getObject());
                        target.addComponent(AjaxDataTablePage.this.getAjaxdataTable());
                    }
                });
            }
        });
        //

        this.addCustomColumns(columns);

        this.setAjaxdataTable(new AjaxFallbackDefaultDataTable<T>(id, columns, new GenericSortableDataProvider<T>(id,
                this.getSearch(), sortName), SearchModel.PAGE_SIZE));
        this.setSortableAjaxWicket(new WebMarkupContainer("markup"));
        // SortableAjax sortableAjaxBehavior = new SortableAjax();
        // sortableAjaxBehavior.getSortableBehavior().setConnectWith(".dataview.tr");

        // this.getSortableAjaxWicket().add(sortableAjaxBehavior);
        this.getSortableAjaxWicket().add(this.getAjaxdataTable());

    }

    /**
     * Agrega columnas personalizadas a las columnas ya existentes, pasadas como
     * parametro.
     */
    @SuppressWarnings("unused")
    protected void addCustomColumns(final List<IColumn<T>> columns) {
        // el PMD me dice que documente esto (?)
    }

    private PropertyColumn<T> createPropertyColumn(final String field) {
        return new PropertyColumn<T>(new Model<String>(StringUtils.capitalize(field)), field);
    }

    public Component getResultSection() {
        return resultSection;
    }

    @Override
    public void setResultSection(final Component resultSection) {
        this.resultSection = resultSection;
    }

    public Component getParentPage() {
        return parentPage;
    }

    @Override
    public void setParentPage(final Component page) {
        this.parentPage = page;
    }

    public SearchModel<T> getSearch() {
        return search;
    }

    public void setSearch(final SearchModel<T> search) {
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

    public void setSortableAjaxWicket(final WebMarkupContainer sortableAjaxWicket) {
        this.sortableAjaxWicket = sortableAjaxWicket;
    }

    @Override
    public WebMarkupContainer getSortableAjaxWicket() {
        return sortableAjaxWicket;
    }

    public void setParentPanel(final Panel parentPanel) {
        this.parentPanel = parentPanel;
    }

    public Panel getParentPanel() {
        return parentPanel;
    }

    public AjaxCallBack<Component> getCallBack() {
        return callBack;
    }

    public void setCallBack(final AjaxCallBack<Component> callBack) {
        this.callBack = callBack;
    }
}
