package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.Component;
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
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.service.DTO;

/**
 * Representa un panel de creacion/edicion de alguna entidad.
 */
public abstract class EntityPanel<T extends DTO> extends AbstractCallbackPanel<T> {

    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    /**
     * Nos indica si se esta editando el objeto. Si es <code>false</code>,
     * entonces se esta usando el panel para crear un objeto nuevo.
     */
    private boolean editMode;

    /* *************************** CONSTRUCTORS *************************** */

    public EntityPanel(final String id, final T model, final boolean isEditMode) {
        super(id, model);
        this.editMode = isEditMode;
    }

    public EntityPanel(final String id, final T model) {
        this(id, model, false);
    }

    /* **************************** ACCESSORS ***************************** */

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(final boolean editMode) {
        this.editMode = editMode;
    }

    /* **************************** OPERATIONS **************************** */

    public void init(final AjaxCallBack<Component> callback, final AbstractSearchPanel<?> backPanel) {
        super.init(callback, backPanel);
        this.beforeConstruct();
        this.setFeedbackPanel(new FeedbackPanel(this.getFeedbackPanelWicketId()));
        Form<T> form = new Form<T>(this.getFormWicketId(), new CompoundPropertyModel<T>(this.getModelObject()));
        this.add(form);
        this.addFields(form);
        this.addButtons(form);
    }

    /* **************************** OPERATIONS **************************** */

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
                    SearchModel<T> search = (SearchModel<T>) EntityPanel.this.getBackPanel().getDefaultModelObject();
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

    protected void beforeConstruct() {
        new Object(); // esto lo hago porque sino el PMD chilla
    }

    /* ************************ ABSTRACT METHODS ************************** */

    /**
     * Agrega los campos deseados al formulario dado como parametro. Para
     * agregar el {@link FeedbackPanel}, solo basta con ejecutar: <br>
     * <code>form.add(this.getFeedbackPanel());</code>
     */
    protected abstract void addFields(Form<T> form);
}
