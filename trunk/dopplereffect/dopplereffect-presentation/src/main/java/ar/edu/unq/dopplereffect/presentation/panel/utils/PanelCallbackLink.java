package ar.edu.unq.dopplereffect.presentation.panel.utils;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.Link;
import org.odlabs.wiquery.ui.button.ButtonBehavior;

import ar.edu.unq.dopplereffect.presentation.pages.basic.WebComponentFactory;
import ar.edu.unq.dopplereffect.presentation.util.CallBack;

/**
 * TODO: description
 */
public class PanelCallbackLink extends Link<Object> {
    private static final long serialVersionUID = 1L;

    private WebComponentFactory<Component> component;

    private CallBack<Component> callBack;

    public PanelCallbackLink(final String id, final CallBack<Component> callBack, final Component component) {
        this(id, callBack, new WebComponentFactory<Component>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component createPage() {
                return component;
            }
        });
    }

    public PanelCallbackLink(final String id, final CallBack<Component> callBack,
            final WebComponentFactory<Component> component) {
        super(id);
        this.add(new ButtonBehavior());
        this.setComponent(component);
        this.setCallBack(callBack);
    }

    @Override
    public void onClick() {
        this.getCallBack().execute(this.getComponent().createPage());
    }

    public void setCallBack(final CallBack<Component> callBack) {
        this.callBack = callBack;
    }

    public CallBack<Component> getCallBack() {
        return callBack;
    }

    public void setComponent(final WebComponentFactory<Component> component) {
        this.component = component;
    }

    public WebComponentFactory<Component> getComponent() {
        return component;
    }
}