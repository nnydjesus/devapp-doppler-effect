package ar.edu.unq.dopplereffect.presentation.pages.basic;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPage2;
import ar.edu.unq.dopplereffect.presentation.search.Search;

public abstract class EntityPage<T> extends NavigableWebPage<T> {

    private FeedbackPanel feedbackPanel;

    private boolean editMode;

    public EntityPage(final T model, final AbstractSearchPage2<T, ? extends Search<T>> previousPage,
            final boolean editMode) {
        super(model, previousPage);
        this.editMode = editMode;
        this.feedbackPanel = new FeedbackPanel(this.getFeedbackPanelWicketId());
        Form<T> form = new Form<T>(this.getFormWicketId(), new CompoundPropertyModel<T>(this.getModelObject()));
        this.add(form);
        this.addFields(form);
        this.addButtons(form);
    }

    public EntityPage(final T model, final AbstractSearchPage2<T, ? extends Search<T>> previousPage) {
        this(model, previousPage, false);
    }

    public FeedbackPanel getFeedbackPanel() {
        return feedbackPanel;
    }

    public boolean isEditMode() {
        return editMode;
    }

    protected void addButtons(final Form<T> form) {
        form.add(this.makeAcceptButton());
        form.add(this.makeCancelButton());
    }

    private Button makeAcceptButton() {
        return new Button(this.getAcceptButtonWicketId()) {

            private static final long serialVersionUID = 3631325660303690307L;

            @Override
            @SuppressWarnings("unchecked")
            public void onSubmit() {
                try {
                    // invoca la logica de negocio
                    T object = EntityPage.this.getModelObject();
                    Search<T> search = (Search<T>) EntityPage.this.getPreviousPage().getDefaultModelObject();
                    if (EntityPage.this.isEditMode()) {
                        search.update(object);
                    } else {
                        search.save(object);
                    }
                    // navegacion: vuelve a la pagina de busqueda.
                    this.setResponsePage(EntityPage.this.getPreviousPage());
                } catch (UserException e) {
                    // en caso de excepcion de negocio muestra el mensaje como
                    // un error.
                    EntityPage.this.getFeedbackPanel().error(e.getMessage());
                }
            }
        };
    }

    private Button makeCancelButton() {
        Button button = new Button(this.getCancelButtonWicketId()) {

            private static final long serialVersionUID = -3180010882527791755L;

            @Override
            public void onSubmit() {
                this.setResponsePage(EntityPage.this.getPreviousPage());
            }
        };
        button.setDefaultFormProcessing(false);
        return button;
    }

    protected String getAcceptButtonWicketId() {
        return "accept"; // default value
    }

    protected String getCancelButtonWicketId() {
        return "cancel"; // default value
    }

    protected String getFeedbackPanelWicketId() {
        return "feedbackPanel"; // default value
    }

    protected String getFormWicketId() {
        return "form"; // default value
    }

    /**
     * Agrega los campos deseados al formulario dado como parametro. Para
     * agregar el {@link FeedbackPanel}, solo basta con ejecutar: <br>
     * <code>form.add(this.getFeedbackPanel());</code>
     */
    protected abstract void addFields(Form<T> form);
}
