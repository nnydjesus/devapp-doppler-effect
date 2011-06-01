package ar.edu.unq.dopplereffect.presentation.panel.utils;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;

/**
 */
public abstract class AbstractCallbackPanel<T> extends AbstractPanel<T> {
    private static final long serialVersionUID = 1L;

    private AjaxCallBack<Component> callback;

    public AbstractCallbackPanel(final String id) {
        super(id);
    }

    public AbstractCallbackPanel(final String id, final AjaxCallBack<Component> parent, final T model) {
        super(id, model);
        this.setOutputMarkupId(true);
        this.callback = parent;
    }

    public AjaxCallBack<Component> getCallback() {
        return callback;
    }

    public void setCallback(final AjaxCallBack<Component> callback) {
        this.callback = callback;
    }

    public void back(final AjaxRequestTarget ajaxTarget) {
        callback.execute(ajaxTarget, this);
    }

}
