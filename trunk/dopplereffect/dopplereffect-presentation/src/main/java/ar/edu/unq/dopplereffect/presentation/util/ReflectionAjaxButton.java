package ar.edu.unq.dopplereffect.presentation.util;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.odlabs.wiquery.ui.button.ButtonBehavior;

import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 * Boton Ajax que ejecuta un metodo dado en el modelo cuando es presionado.
 */
public class ReflectionAjaxButton<T> extends AjaxButton {

    private static final long serialVersionUID = 8596073328172540060L;

    /* ************************ INSTANCE VARIABLES ************************ */

    /**
     * Metodo a ejecutarse en el modelo.
     */
    private String action;

    /**
     * Componente a agregar via Ajax.
     */
    private Component ajaxTarget;

    private T object;

    /* *************************** CONSTRUCTORS *************************** */

    public ReflectionAjaxButton(final String id, final T objectModel, final Component ajaxTarget,
            final IModel<String> model) {
        this(id, id, objectModel, ajaxTarget, model);
    }

    public ReflectionAjaxButton(final String id, final T objectModel, final Component ajaxTarget) {
        this(id, objectModel, ajaxTarget, new Model<String>(""));
    }

    public ReflectionAjaxButton(final String id, final String action, final T objectModel,
            final Component anAjaxTarget, final IModel<String> model) {
        super(id, model);
        this.add(new ButtonBehavior());
        this.setAction(action);
        this.ajaxTarget = anAjaxTarget;
        this.setObject(objectModel);
    }

    /* **************************** ACCESSORS ***************************** */

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public T getObject() {
        return object;
    }

    public void setObject(final T object) {
        this.object = object;
    }

    public Component getAjaxTarget() {
        return ajaxTarget;
    }

    public void setAjaxTarget(final Component ajaxTarget) {
        this.ajaxTarget = ajaxTarget; // solo porque rompe las bolas el PMD
    }

    /* ************************ ABSTRACT METHODS ************************** */

    @Override
    protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
        this.execute(target);
        target.addComponent((Component) this.getObject());
    }

    protected void execute(final AjaxRequestTarget target) {
        new HandlerErrorAction() {

            private static final long serialVersionUID = 1L;

            @Override
            public void onExecute() {
                ReflectionUtils.invokeMethod(ReflectionAjaxButton.this.getObject(),
                        ReflectionAjaxButton.this.getAction());
            }

        }.execute();
    }

    @Override
    protected void onError(final AjaxRequestTarget target, final Form<?> arg1) {
        target.addComponent(this.getAjaxTarget());
    }
}
