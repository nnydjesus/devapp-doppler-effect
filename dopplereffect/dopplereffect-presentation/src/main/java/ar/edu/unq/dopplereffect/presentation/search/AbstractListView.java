package ar.edu.unq.dopplereffect.presentation.search;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import ar.edu.unq.dopplereffect.presentation.abm.ABMAbstract;
import ar.edu.unq.dopplereffect.presentation.abm.AbstractWebPage;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 *
 */
public class AbstractListView<T, B> extends ListView<T> {
    private static final long serialVersionUID = 1L;

    private Component resultSection;

    private AbstractWebPage<B> parentPage;

    private B search;

    private Class<T> classModel;

    private List<String> fields;

    private Class<? extends ABMAbstract<T>> abmClass;

    public AbstractListView(final String id, final Class<T> clazz, final List<String> fields,
            final Class<? extends ABMAbstract<T>> abm) {
        super(id);
        this.setClassModel(clazz);
        this.fields = fields;
        this.abmClass = abm;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected IModel getListItemModel(final IModel listViewModel, final int index) {
        return new CompoundPropertyModel(((List<?>) listViewModel.getObject()).get(index));
    }

    @Override
    protected void populateItem(final ListItem<T> item) {

        for (String field : fields) {
            item.add(new Label(field));
        }

        item.add(new Link("edit") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                ABMAbstract<T> page = ReflectionUtils.instanciate(abmClass, AbstractListView.this.getParentPage(), this
                        .getParent().getDefaultModelObject());
                this.setResponsePage(page);
            }
        });

        item.add(new AjaxLink("delete") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                T model = (T) this.getParent().getDefaultModelObject();
                ((Search<T>) AbstractListView.this.getBuscador()).remove(model);
                target.addComponent(AbstractListView.this.getResultSection());
            }
        });

    }

    public Component getResultSection() {
        return resultSection;
    }

    public void setResultSection(final Component resultSection) {
        this.resultSection = resultSection;
    }

    public AbstractWebPage<B> getParentPage() {
        return parentPage;
    }

    public void setParentPage(final AbstractWebPage<B> page) {
        this.parentPage = page;
    }

    public B getBuscador() {
        return search;
    }

    public void setSearch(final B search) {
        this.search = search;
    }

    public void setClassModel(Class<T> classModel) {
        this.classModel = classModel;
    }

    public Class<T> getClassModel() {
        return classModel;
    }
}
