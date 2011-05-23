package ar.edu.unq.dopplereffect.presentation.search;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import ar.edu.unq.dopplereffect.presentation.pages.basic.EntityPage;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 * Representa un listado de objetos, con links de edicion y borrado.
 */
public class CustomListView<T, S extends Search<T>> extends ListView<T> {

    private static final long serialVersionUID = 8535469505375388726L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private AbstractSearchPage2<T, S> parentPage;

    private S search;

    private Component resultSection;

    private String[] modelFields;

    private Class<? extends EntityPage<T>> entityPageClass;

    /* *************************** CONSTRUCTORS *************************** */

    public CustomListView(final String id, final String[] modelFields,
            final Class<? extends EntityPage<T>> entityPageClass) {
        super(id);
        this.modelFields = modelFields;
        this.entityPageClass = entityPageClass;
    }

    /* **************************** ACCESSORS ***************************** */

    public AbstractSearchPage2<T, S> getParentPage() {
        return parentPage;
    }

    public void setParentPage(final AbstractSearchPage2<T, S> parentPage) {
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

    public void setResultSection(final WebMarkupContainer panel) {
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
        for (String field : modelFields) {
            item.add(new Label(field));
        }
        item.add(this.makeEditButton());
        item.add(this.makeDeleteButton());
    }

    private Component makeEditButton() {
        return new Link<Object>(this.getEditButtonWicketId()) {

            private static final long serialVersionUID = 635100980784300935L;

            @Override
            @SuppressWarnings("synthetic-access")
            public void onClick() {
                WebPage page = ReflectionUtils.instanciate(entityPageClass, CustomListView.this.getParentPage(),
                        this.getParent().getDefaultModelObject(), true);
                this.setResponsePage(page);
            }
        };
    }

    private Component makeDeleteButton() {
        return new AjaxLink<Object>(this.getDeleteButtonWicketId()) {

            private static final long serialVersionUID = -8948246124445479612L;

            @Override
            @SuppressWarnings("unchecked")
            public void onClick(final AjaxRequestTarget target) {
                T object = (T) this.getParent().getDefaultModelObject();
                CustomListView.this.getSearch().remove(object);
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
}