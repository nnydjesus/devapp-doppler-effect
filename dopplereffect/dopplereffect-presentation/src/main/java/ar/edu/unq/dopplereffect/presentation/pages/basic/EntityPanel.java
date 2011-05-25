package ar.edu.unq.dopplereffect.presentation.pages.basic;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.search.Search;

public abstract class EntityPanel<T> extends NavigablePanel<T> {
    private static final long serialVersionUID = 1L;

    private boolean editMode;

    public EntityPanel(final String id, final T model,
            final AbstractCallbackPanel<? extends Serializable> previousPage, final boolean editMode) {
        super(id, model, previousPage);
        this.setEditMode(editMode);
        this.setFeedbackPanel(new FeedbackPanel(this.getFeedbackPanelWicketId()));
        Form<T> form = new Form<T>(this.getFormWicketId(), new CompoundPropertyModel<T>(this.getModelObject()));
        this.add(form);
        this.addFields(form);
        this.addButtons(form);
    }

    public EntityPanel(final String id, final T model, final AbstractCallbackPanel<? extends Serializable> previousPage) {
        this(id, model, previousPage, false);
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
                    T object = EntityPanel.this.getModelObject();
                    Search<T> search = (Search<T>) EntityPanel.this.getPreviousPage().getDefaultModelObject();
                    if (EntityPanel.this.isEditMode()) {
                        search.update(object);
                    } else {
                        search.save(object);
                    }
                    // navegacion: vuelve a la pagina de busqueda.
                    EntityPanel.this.back();
                    // this.setResponsePage(EntityPage.this.getPreviousPage());
                    EntityPanel.this.getPreviousPage().back();
                } catch (UserException e) {
                    // en caso de excepcion de negocio muestra el mensaje como
                    // un error.
                    EntityPanel.this.getFeedbackPanel().error(e.getMessage());
                }
            }
        };
    }

    private Button makeCancelButton() {
        Button button = new Button(this.getCancelButtonWicketId()) {

            private static final long serialVersionUID = -3180010882527791755L;

            @Override
            public void onSubmit() {
                EntityPanel.this.back();
                // this.setResponsePage(EntityPage.this.getPreviousPage());
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
        return this.getModelObject().getClass().getSimpleName().toLowerCase() + "Form"; // default
                                                                                        // value
    }

    /**
     * Agrega los campos deseados al formulario dado como parametro. Para
     * agregar el {@link FeedbackPanel}, solo basta con ejecutar: <br>
     * <code>form.add(this.getFeedbackPanel());</code>
     */
    protected abstract void addFields(Form<T> form);

    public void setEditMode(final boolean editMode) {
        this.editMode = editMode;
    }
}
