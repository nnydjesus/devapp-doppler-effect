package ar.edu.unq.dopplereffect.presentation.panel.utils;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;

/**
 */
public abstract class AbstractCallbackPanel<T> extends AbstractPanel<T> {
    private static final long serialVersionUID = 1L;

    private AjaxCallBack<Component> callback;

    private AbstractPanel<?> backPanel;

    public AbstractCallbackPanel(final String id) {
        super(id);
    }

    public AbstractCallbackPanel(final String id, final T model) {
        super(id, model);
    }

    public void init(final AjaxCallBack<Component> aCallback, final AbstractPanel<?> aBackPanel) {
        this.callback = aCallback;
        this.backPanel = aBackPanel;
        if (this.showTitle()) {
            this.add(new Label(this.getTitleWicketId(), new StringResourceModel(this.getTitleLocaleKey(),
                    new Model<String>(""))));
        }
    }

    public AjaxCallBack<Component> getCallback() {
        return callback;
    }

    public void setCallback(final AjaxCallBack<Component> callback) {
        this.callback = callback;
    }

    public void back(final AjaxRequestTarget ajaxTarget) {
        callback.execute(ajaxTarget, backPanel);
    }

    public void setBackPanel(final AbstractPanel<?> backPanel) {
        this.backPanel = backPanel;
    }

    public AbstractPanel<?> getBackPanel() {
        return backPanel;
    }

    public void reset() {
        // solo para que el findbugs no chille
        new Object();
    }

    /**
     * Redefinir en caso de querer un titulo.
     */
    protected boolean showTitle() {
        return false;
    }

    protected String getTitleWicketId() {
        return "panel_title";
    }

    protected String getTitleLocaleKey() {
        return this.getTitleWicketId();
    }

}
