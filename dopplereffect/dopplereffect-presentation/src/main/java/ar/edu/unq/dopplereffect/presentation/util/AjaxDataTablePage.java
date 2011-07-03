package ar.edu.unq.dopplereffect.presentation.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.Localizer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import ar.edu.unq.dopplereffect.presentation.panel.AjaxActionPanel;
import ar.edu.unq.dopplereffect.presentation.panel.AjaxActionPanelWithConfirm;
import ar.edu.unq.dopplereffect.presentation.panel.EntityPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.service.DTO;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

public class AjaxDataTablePage<T extends DTO, S extends SearchModel<T>> implements Serializable, ITable {

    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private Component resultSection;

    private AbstractSearchPanel<?> parentPage;

    private S searchModel;

    private List<String> fields;

    private Class<? extends Component> abmClass;

    private AjaxFallbackDefaultDataTable<T> ajaxdataTable;

    private WebMarkupContainer sortableAjaxWicket;

    private AbstractPanel<?> parentPanel;

    private AjaxCallBack<Component> callBack;

    private String id;

    private String sortName;

    /* *************************** CONSTRUCTORS *************************** */

    public AjaxDataTablePage(final AbstractPanel<?> parent, final String id, final String sortName,
            final S searchModel, final AjaxCallBack<Component> callBack, final List<String> fields,
            final Class<? extends Component> abmClass) {

        this.setId(id);
        this.setSortName(sortName);
        this.setSearchModel(searchModel);
        this.callBack = callBack;
        this.parentPanel = parent;
        this.fields = fields;
        this.abmClass = abmClass;
    }

    /* **************************** ACCESSORS ***************************** */

    public Component getResultSection() {
        return resultSection;
    }

    public void setResultSection(final Component resultSection) {
        this.resultSection = resultSection;
    }

    public AbstractSearchPanel<?> getParentPage() {
        return parentPage;
    }

    @Override
    public void setParentPage(final AbstractSearchPanel<?> page) {
        this.parentPage = page;
    }

    public AjaxFallbackDefaultDataTable<T> getAjaxdataTable() {
        return ajaxdataTable;
    }

    public void setAjaxdataTable(final AjaxFallbackDefaultDataTable<T> ajaxdataTable) {
        this.ajaxdataTable = ajaxdataTable;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(final List<String> fields) {
        this.fields = fields;
    }

    public Class<? extends Component> getAbmClass() {
        return abmClass;
    }

    public void setAbmClass(final Class<? extends Component> abmClass) {
        this.abmClass = abmClass;
    }

    @Override
    public WebMarkupContainer getSortableAjaxWicket() {
        return sortableAjaxWicket;
    }

    public void setSortableAjaxWicket(final WebMarkupContainer sortableAjaxWicket) {
        this.sortableAjaxWicket = sortableAjaxWicket;
    }

    public AbstractPanel<?> getParentPanel() {
        return parentPanel;
    }

    public void setParentPanel(final AbstractPanel<?> parentPanel) {
        this.parentPanel = parentPanel;
    }

    public AjaxCallBack<Component> getCallBack() {
        return callBack;
    }

    public void setCallBack(final AjaxCallBack<Component> callBack) {
        this.callBack = callBack;
    }

    public S getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(final S searchModel) {
        this.searchModel = searchModel;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(final String sortName) {
        this.sortName = sortName;
    }

    /* **************************** OPERATIONS **************************** */

    public void init() {
        ArrayList<IColumn<T>> columns = new ArrayList<IColumn<T>>();

        for (String field : this.getFields()) {
            columns.add(this.createPropertyColumn(field));
        }
        this.addCustomColumns(columns);

        columns.add(new AbstractColumn<T>(new StringResourceModel("header.edit", new Model<String>(""))) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId,
                    final IModel<T> model) {
                cellItem.add(new AjaxActionPanel(componentId, "edit.png") {
                    private static final long serialVersionUID = 1L;

                    @Override
                    @SuppressWarnings("synthetic-access")
                    public void onAction(final AjaxRequestTarget target) {
                        Component page = AjaxDataTablePage.this.createEditPanel(model);
                        callBack.execute(target, page);
                    }

                });
            }
        });

        columns.add(new AbstractColumn<T>(new StringResourceModel("header.delete", new Model<String>(""))) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId,
                    final IModel<T> rowModel) {
                String confirmText = new Localizer().getString("confirm.delete", AjaxDataTablePage.this.getParentPage());
                cellItem.add(new AjaxActionPanelWithConfirm(componentId, "delete.png", confirmText) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onAction(final AjaxRequestTarget target) {
                        AjaxDataTablePage.this.getSearchModel().remove(rowModel.getObject());
                        target.addComponent(AjaxDataTablePage.this.getAjaxdataTable());
                    }
                });
            }
        });
        this.setAjaxdataTable(new AjaxFallbackDefaultDataTable<T>(this.getId(), columns,
                new GenericSortableDataProvider<T>(this.getId(), this.getSearchModel(), this.getSortName()),
                getPageSize()));
        this.setSortableAjaxWicket(new WebMarkupContainer("markup"));
        this.getSortableAjaxWicket().add(this.getAjaxdataTable());
    }

    protected int getPageSize() {
        return SearchModel.PAGE_SIZE;
    }

    protected Component createEditPanel(final IModel<T> model) {
        EntityPanel<? extends DTO> entityPanel = (EntityPanel<?>) ReflectionUtils.instanciate(this.getAbmClass(), this
                .getParentPanel().getId(), this.getSearchModel().createEditDTO(model.getObject()));
        entityPanel.init(this.getCallBack(), this.getParentPage());
        return entityPanel;
    }

    /* ************************* PRIVATE METHODS ************************** */

    /**
     * Agrega columnas personalizadas a las columnas ya existentes, pasadas como
     * parametro.
     */
    protected void addCustomColumns(final List<IColumn<T>> columns) {
        // x
    }

    private PropertyColumn<T> createPropertyColumn(final String field) {
        return new PropertyColumn<T>(new StringResourceModel("header." + field, new Model<String>("")), field);
    }
}
