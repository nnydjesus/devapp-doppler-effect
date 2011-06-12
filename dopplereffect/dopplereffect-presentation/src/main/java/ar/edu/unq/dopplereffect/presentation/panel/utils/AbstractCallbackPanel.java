package ar.edu.unq.dopplereffect.presentation.panel.utils;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;

/**
 */
public abstract class AbstractCallbackPanel<T> extends AbstractPanel<T> {
    private static final long serialVersionUID = 1L;

    private AjaxCallBack<Component> callback;

    private Panel backPanel;

    public AbstractCallbackPanel(final String id) {
        super(id);
    }

    public AbstractCallbackPanel(final String id, final AjaxCallBack<Component> parent, final Panel backPanel,
            final T model) {
        super(id, model);
        this.setOutputMarkupId(true);
        this.callback = parent;
        this.backPanel = backPanel;
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

    public void setBackPanel(final Panel backPanel) {
        this.backPanel = backPanel;
    }

    public Panel getBackPanel() {
        return backPanel;
    }

    public void reset() {
    }

}
