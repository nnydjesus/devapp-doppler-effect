package ar.edu.unq.dopplereffect.presentation.util;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.odlabs.wiquery.ui.button.ButtonBehavior;

import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 * Boton Ajax que ejecuta un metodo dado en el modelo cuando es presionado.
 */
public class ReflectionAjaxButton<T> extends AjaxButton {

    private static final long serialVersionUID = 8596073328172540060L;

    /**
     * Metodo a ejecutarse en el modelo.
     */
    private String action;

    /**
     * Componente a agregar via Ajax.
     */
    private final Component ajaxTarget;

    private T object;

    public ReflectionAjaxButton(final String id, final Form<T> form, final Component ajaxTarget) {
        this(id, id, form, ajaxTarget);
        this.add(new ButtonBehavior());

    }

    public ReflectionAjaxButton(final String id, final String action, final Form<T> form, final Component anAjaxTarget) {
        super(id);
        this.setAction(action);
        this.ajaxTarget = anAjaxTarget;
        this.setObject(form.getModel().getObject());
    }

    @Override
    protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
        this.execute();
        target.addComponent(this.getAjaxTarget());
    }

    protected void execute() {
        ReflectionUtils.invokeMethod(this.getObject(), this.getAction());
    }

    @Override
    protected void onError(final AjaxRequestTarget arg0, final Form<?> arg1) {
        // Hacer algo cuando hay un error

    }

    public void setAction(final String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public Component getAjaxTarget() {
        return ajaxTarget;
    }

    public void setObject(final T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }
}
