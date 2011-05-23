package ar.edu.unq.dopplereffect.presentation.pages.basic;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

/**
 * Clase que define un modelo basico via {@link CompoundPropertyModel}.
 */
public class AbstractWebPage<T> extends WebPage {

    public AbstractWebPage(final T model) {
        super(new CompoundPropertyModel<T>(model));
    }

    protected Form<T> createForm(final String formName) {
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