package ar.edu.unq.dopplereffect.presentation.abm;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class AbstractWebPage<T> extends WebPage {

    public AbstractWebPage(final T model) {
        super(new CompoundPropertyModel<T>(model));
    }

    protected Form<T> createForm(final String formName, final T formModel) {
        return new Form<T>(formName, this.getModel());
    }

    public T getModelObject() {
        return this.getModel().getObject();
    }

    @SuppressWarnings("unchecked")
    protected IModel<T> getModel() {
        return (IModel<T>) this.getDefaultModel();
    }
}