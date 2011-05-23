package ar.edu.unq.dopplereffect.presentation.component;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEventSink;
import org.apache.wicket.markup.html.form.Form;

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

    private T model;
    
    public ReflectionAjaxButton(final String id, final Form<T> form, final Component ajaxTarget) {
    	this(id, id, form, ajaxTarget);
    	
    }

    public ReflectionAjaxButton(final String id, final String action, final Form<T> form, final Component ajaxTarget) {
        super(id);
        this.action = action;
        this.ajaxTarget = ajaxTarget;
        this.model = form.getModel().getObject();
    }

    @Override
    protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
        this.execute();
        target.addComponent(this.ajaxTarget);
    }

    protected void execute() {
        ReflectionUtils.invokeMethod(this.model, this.action);
    }

	@Override
	protected void onError(AjaxRequestTarget arg0, Form<?> arg1) {
		//Hacer algo cuando hay un error
		
	}
}
