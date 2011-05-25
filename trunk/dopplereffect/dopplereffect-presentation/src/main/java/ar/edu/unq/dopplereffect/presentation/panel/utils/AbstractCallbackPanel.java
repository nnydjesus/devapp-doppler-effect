package ar.edu.unq.dopplereffect.presentation.panel.utils;

import org.apache.wicket.Component;

import ar.edu.unq.dopplereffect.presentation.util.CallBack;

/**
 */
public abstract class AbstractCallbackPanel<T> extends AbstractPanel<T> {
    private static final long serialVersionUID = 1L;

    private CallBack<Component> callback;

    public AbstractCallbackPanel(final String id) {
        super(id);
    }

    public AbstractCallbackPanel(final String id, final CallBack<Component> parent, final T model) {
        super(id, model);
        this.setOutputMarkupId(true);
        this.callback = parent;
    }

    public CallBack<Component> getCallback() {
        return callback;
    }

    public void setCallback(final CallBack<Component> callback) {
        this.callback = callback;
    }

    public void back() {
        callback.execute(this);
    }

}
