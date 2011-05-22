package ar.edu.unq.dopplereffect.presentation.abm;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

public abstract class ABMAbstract<T> extends WebPage {

    private FeedbackPanel feedbackPanel;

    private WebPage laPaginaDeLaQueMeLlamaron;

    private T model;

    public WebPage getLaPaginaDeLaQueMeLlamaron() {
        return laPaginaDeLaQueMeLlamaron;
    }

    public void setLaPaginaDeLaQueMeLlamaron(final WebPage laPaginaDeLaQueMeLlamaron) {
        this.laPaginaDeLaQueMeLlamaron = laPaginaDeLaQueMeLlamaron;
    }

    /**
     * Crea el modelo del formulario que sera' el caso de uso.
     */

    protected CompoundPropertyModel<T> createModel() {
        return new CompoundPropertyModel<T>(this.getModel());
    }

    /**
     * Crea y agrega los controles para editar el nuevo cliente.
     */
    protected abstract void addFields(Form<T> form);

    /**
     * Crea y agrega los botones: aceptar y cancelar.
     */

    public FeedbackPanel setFeedbackPanel(final FeedbackPanel feedbackPanel) {
        this.feedbackPanel = feedbackPanel;
        return feedbackPanel;
    }

    public FeedbackPanel getFeedbackPanel() {
        return feedbackPanel;
    }

    public T getModel() {
        return model;
    }

    public void setModel(final T model) {
        this.model = model;
    }

    /**
     * Crea y agrega los botones: aceptar y cancelar.
     */
    protected void addButtons(final Form<T> form) {
        form.add(new Button("acept") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
            }
        });

        Button button = new Button("cancel") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                this.setResponsePage(ABMAbstract.this.getLaPaginaDeLaQueMeLlamaron());
            }
        };
        button.setDefaultFormProcessing(false);
        form.add(button);

    }

}
