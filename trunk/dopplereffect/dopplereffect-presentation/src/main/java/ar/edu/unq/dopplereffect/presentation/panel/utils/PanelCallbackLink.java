package ar.edu.unq.dopplereffect.presentation.panel.utils;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.ui.button.ButtonBehavior;

import ar.edu.unq.dopplereffect.presentation.pages.basic.WebComponentFactory;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.HandlerErrorAction;

public class PanelCallbackLink extends AjaxLink<String> {
    private static final long serialVersionUID = 1L;

    private WebComponentFactory<Component> component;

    private AjaxCallBack<Component> callBack;

    public PanelCallbackLink(final String id, final AjaxCallBack<Component> callBack, final Component component,
            final IModel<String> model) {
        this(id, callBack, model, new WebComponentFactory<Component>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component createPage() {
                return component;
            }
        });
    }

    public PanelCallbackLink(final String id, final AjaxCallBack<Component> callBack, final IModel<String> model,
            final WebComponentFactory<Component> component) {
        super(id, model);
        this.add(new ButtonBehavior());
        this.setComponent(component);
        this.setCallBack(callBack);
    }

    @Override
    public void onClick(final AjaxRequestTarget target) {
        new HandlerErrorAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onExecute() {
                PanelCallbackLink.this.getCallBack()
                        .execute(target, PanelCallbackLink.this.getComponent().createPage());
            }
        }.execute();

    }

    public void setCallBack(final AjaxCallBack<Component> callBack) {
        this.callBack = callBack;
    }

    public AjaxCallBack<Component> getCallBack() {
        return callBack;
    }

    public void setComponent(final WebComponentFactory<Component> component) {
        this.component = component;
    }

    public WebComponentFactory<Component> getComponent() {
        return component;
    }
}