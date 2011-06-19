package ar.edu.unq.dopplereffect.presentation.util;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.odlabs.wiquery.ui.button.ButtonBehavior;

import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 */
public class ReflextionAjaxLink<T> extends AjaxLink<String> {

    private static final long serialVersionUID = 8596073328172540060L;

    /**
     * Metodo a ejecutarse en el modelo.
     */
    private String action;

    /**
     * Componente a agregar via Ajax.
     */
    private Component ajaxTarget;

    private T object;

    public ReflextionAjaxLink(final String id, final String action, final T host, final Component ajaxTarget) {
        this(id, action, host, ajaxTarget, new Model<String>(""));
    }

    public ReflextionAjaxLink(final String id, final String action, final T host, final Component anAjaxTarget,
            final IModel<String> model) {
        super(id, model);
        this.add(new ButtonBehavior());
        this.setAction(action);
        this.ajaxTarget = anAjaxTarget;
        this.setObject(host);
    }

    @Override
    public void onClick(final AjaxRequestTarget target) {
        this.execute();
        target.addComponent(this.getAjaxTarget());
    }

    protected void execute() {
        ReflectionUtils.invokeMethod(this.getObject(), this.getAction());
    }

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
        // solo porque rompe las bolas el PMD
        this.ajaxTarget = ajaxTarget;
    }

}
