package ar.edu.unq.dopplereffect.presentation.search;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import ar.edu.unq.dopplereffect.presentation.panel.utils.SortableAjax;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.ITable;
import ar.edu.unq.dopplereffect.presentation.util.SelectTable;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 * Representa un listado de objetos, con links de edicion y borrado.
 */
public class CustomListView<T, S> extends ListView<T> implements ITable {

    private static final long serialVersionUID = 8535469505375388726L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private Component parentPage;

    private S search;

    private Component resultSection;

    private List<String> modelFields;

    private Class<? extends Component> entityPageClass;

    private WebMarkupContainer sortableAjaxWicket;

    private AjaxCallBack<Component> callback;

    private Panel parentPanel;

    /* *************************** CONSTRUCTORS *************************** */

    public CustomListView(final Panel parent, final String id, final List<String> modelFields,
            final Class<? extends Component> entityPageClass, final AjaxCallBack<Component> aCallBack) {
        super(id);
        this.setParentPanel(parent);
        this.callback = aCallBack;
        this.setModelFields(modelFields);
        this.setEntityPageClass(entityPageClass);

        this.setSortableAjaxWicket(new WebMarkupContainer("markup"));
        SortableAjax sortableAjaxBehavior = new SortableAjax();
        sortableAjaxBehavior.getSortableBehavior().setConnectWith(".connectedSortable");

        SelectTable selectTable = new SelectTable();

        this.getSortableAjaxWicket().add(sortableAjaxBehavior);
        this.sortableAjaxWicket.add(selectTable);
        this.getSortableAjaxWicket().add(this);
    }

    /* **************************** ACCESSORS ***************************** */

    public Component getParentPage() {
        return parentPage;
    }

    @Override
    public void setParentPage(final Component parentPage) {
        this.parentPage = parentPage;
    }

    public S getSearch() {
        return search;
    }

    public void setSearch(final S search) {
        this.search = search;
    }

    public Component getResultSection() {
        return resultSection;
    }

    @Override
    public void setResultSection(final Component panel) {
        this.resultSection = panel;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected IModel getListItemModel(final IModel listViewModel, final int index) {
        return new CompoundPropertyModel(((List<?>) listViewModel.getObject()).get(index));
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    protected void populateItem(final ListItem<T> item) {
        for (String field : this.getModelFields()) {
            item.add(new Label(field)); // NOPMD
        }
        item.add(this.makeEditButton());
        item.add(this.makeDeleteButton());
    }

    private Component makeEditButton() {
        return new AjaxLink<Object>(this.getEditButtonWicketId()) {

            private static final long serialVersionUID = 635100980784300935L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                Component page = ReflectionUtils.instanciate(CustomListView.this.getEntityPageClass(),
                        CustomListView.this.getParentPanel().getId(), CustomListView.this.getParentPage(), this
                                .getParent().getDefaultModelObject(), true);
                CustomListView.this.getCallback().execute(target, page);
                // this.setResponsePage(page);
            }
        };
    }

    private Component makeDeleteButton() {
        return new AjaxLink<Object>(this.getDeleteButtonWicketId()) {

            private static final long serialVersionUID = -8948246124445479612L;

            @Override
            // @SuppressWarnings("unchecked")
            public void onClick(final AjaxRequestTarget target) {
                // T object = (T) this.getParent().getDefaultModelObject();
                // CustomListView.this.getSearch().remove(object);
                target.addComponent(CustomListView.this.getResultSection());
            }
        };
    }

    protected String getEditButtonWicketId() {
        return "edit"; // default value
    }

    protected String getDeleteButtonWicketId() {
        return "delete"; // default value
    }

    public void setSortableAjaxWicket(final WebMarkupContainer sortableAjaxWicket) {
        this.sortableAjaxWicket = sortableAjaxWicket;
    }

    @Override
    public WebMarkupContainer getSortableAjaxWicket() {
        return sortableAjaxWicket;
    }

    public void setEntityPageClass(final Class<? extends Component> entityPageClass) {
        this.entityPageClass = entityPageClass;
    }

    public Class<? extends Component> getEntityPageClass() {
        return entityPageClass;
    }

    public void setModelFields(final List<String> modelFields) {
        this.modelFields = modelFields;
    }

    public List<String> getModelFields() {
        return modelFields;
    }

    public void setCallback(final AjaxCallBack<Component> callback) {
        this.callback = callback;
    }

    public AjaxCallBack<Component> getCallback() {
        return callback;
    }

    public void setParentPanel(final Panel parentPanel) {
        this.parentPanel = parentPanel;
    }

    public Panel getParentPanel() {
        return parentPanel;
    }
}