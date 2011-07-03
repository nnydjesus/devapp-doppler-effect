package ar.edu.unq.dopplereffect.presentation.panel.utils;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 */
public class AbstractPanel<T> extends Panel {

    private static final long serialVersionUID = 1L;

    private FeedbackPanel feedbackPanel;

    public AbstractPanel(final String id) {
        super(id, new Model<String>(""));
        this.setOutputMarkupId(true);
    }

    public AbstractPanel(final String id, final T model) {
        super(id, new CompoundPropertyModel<T>(model));
        this.setOutputMarkupId(true);
    }

    /**
     * Crea el modelo del formulario que sera' el caso de uso.
     */

    protected CompoundPropertyModel<T> createModel() {
        return new CompoundPropertyModel<T>(this.getModelObject());
    }

    public FeedbackPanel setFeedbackPanel(final FeedbackPanel aFeedbackPanel) {
        this.feedbackPanel = aFeedbackPanel;
        this.feedbackPanel.setOutputMarkupId(true);
        return aFeedbackPanel;
    }

    public FeedbackPanel getFeedbackPanel() {
        return feedbackPanel;
    }

    protected Form<T> createForm(final String formName) {
        return new Form<T>(formName, this.getModel());
    }

    public T getModelObject() {
        return this.getModel().getObject();
    }

    public void setModelObject(final T object) {
        this.getModel().setObject(object);
    }

    @SuppressWarnings("unchecked")
    protected IModel<T> getModel() {
        return (IModel<T>) this.getDefaultModel();
    }

}
