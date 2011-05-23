package ar.edu.unq.dopplereffect.presentation.component;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

/**
 */
public abstract class AbstractWebPage<T> extends WebPage {

    private FeedbackPanel feedbackPanel;

    private WebPage parentPage;

    public AbstractWebPage() {
    }

    public AbstractWebPage(final WebPage parent, final T model) {
        super(new CompoundPropertyModel<T>(model));
        this.parentPage = parent;
    }

    public WebPage getParentPage() {
        return parentPage;
    }

    public void setParentPage(final WebPage laPaginaDeLaQueMeLlamaron) {
        this.parentPage = laPaginaDeLaQueMeLlamaron;
    }

    /**
     * Crea el modelo del formulario que sera' el caso de uso.
     */

    protected CompoundPropertyModel<T> createModel() {
        return new CompoundPropertyModel<T>(this.getModelObject());
    }

    public FeedbackPanel setFeedbackPanel(final FeedbackPanel feedbackPanel) {
        this.feedbackPanel = feedbackPanel;
        return feedbackPanel;
    }

    public FeedbackPanel getFeedbackPanel() {
        return feedbackPanel;
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
