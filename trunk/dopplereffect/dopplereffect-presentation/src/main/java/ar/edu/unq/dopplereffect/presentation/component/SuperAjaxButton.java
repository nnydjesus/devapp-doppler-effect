package ar.edu.unq.dopplereffect.presentation.component;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;

import ar.edu.unq.tpi.util.common.ReflectionUtils;

public class SuperAjaxButton<T> extends AjaxButton {
    private static final long serialVersionUID = 1L;

    private String action;

    private final Component ajaxTarget;

    private T model;

    public SuperAjaxButton(final String id, final Form<T> form, final Component ajaxTarget) {
        super(id);
        this.action = id;
        this.ajaxTarget = ajaxTarget;
        this.model = form.getModel().getObject();
    }

    @Override
    protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
        // logica de negocio.
        this.execute(this.model);
        // actualizar la seccion de resultado en el cliente.
        target.addComponent(this.ajaxTarget);
    }

    protected void execute(final T model) {
        ReflectionUtils.invokeMethod(model, this.action);
    }
}
