package ar.edu.unq.dopplereffect.presentation.panel;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.odlabs.wiquery.ui.button.ButtonBehavior;

import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.service.DTO;

public abstract class EntityPanel<T extends DTO> extends NavigablePanel<T> {
    private static final long serialVersionUID = 1L;

    private boolean editMode;

    public EntityPanel(final String id, final T model,
            final AbstractCallbackPanel<? extends Serializable> previousPage, final boolean editMode) {
        super(id, model, previousPage);
        this.beforeConstruct();
        this.editMode = editMode;
        this.setFeedbackPanel(new FeedbackPanel(this.getFeedbackPanelWicketId()));
        Form<T> form = new Form<T>(this.getFormWicketId(), new CompoundPropertyModel<T>(this.getModelObject()));
        this.add(form);
        this.addFields(form);
        this.addButtons(form);
    }

    // abstracto porque el PMD se queja
    protected abstract void beforeConstruct();

    public EntityPanel(final String id, final T model, final AbstractCallbackPanel<? extends Serializable> previousPage) {
        this(id, model, previousPage, false);
    }

    public boolean isEditMode() {
        return editMode;
    }

    protected void addButtons(final Form<T> form) {
        form.add(this.makeAcceptButton().add(new ButtonBehavior()));
        form.add(this.makeCancelButton().add(new ButtonBehavior()));
    }

    private Button makeAcceptButton() {
        return new AjaxButton(this.getAcceptButtonWicketId(), new StringResourceModel("aceptbutton", new Model<String>(
                ""))) {

            private static final long serialVersionUID = 3631325660303690307L;

            @Override
            @SuppressWarnings("unchecked")
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                try {
                    // invoca la logica de negocio
                    T object = EntityPanel.this.getModelObject();
                    SearchModel<T> search = (SearchModel<T>) EntityPanel.this.getCallBackPrevuousPanel()
                            .getDefaultModelObject();
                    if (EntityPanel.this.isEditMode()) {
                        search.update(object);
                    } else {
                        search.save(object);
                    }
                    // navegacion: vuelve a la pagina de busqueda.
                    EntityPanel.this.back(target);
                } catch (UserException e) {
                    // en caso de excepcion de negocio muestra el mensaje como
                    // un error.
                    this.error(this.getLocalizer().getString(e.getMessage(), EntityPanel.this));
                }
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                target.addComponent(EntityPanel.this.getFeedbackPanel());
            }
        };
    }

    private Button makeCancelButton() {
        Button button = new AjaxButton(this.getCancelButtonWicketId(), new StringResourceModel("cancelButton",
                new Model<String>(""))) {

            private static final long serialVersionUID = -3180010882527791755L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                EntityPanel.this.back(target);
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
