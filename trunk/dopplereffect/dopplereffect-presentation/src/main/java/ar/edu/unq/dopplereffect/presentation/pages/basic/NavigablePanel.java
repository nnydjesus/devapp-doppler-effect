package ar.edu.unq.dopplereffect.presentation.pages.basic;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.link.Link;

import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;

/**
 * Panel que recuerda el panel anterior, permitiendo asi la navegacion.
 */
public class NavigablePanel<T> extends AbstractPanel<T> {

    private static final long serialVersionUID = 1L;

    private AbstractCallbackPanel<? extends Serializable> callBackPrevuousPanel;

    public NavigablePanel(final String id, final T model, final AbstractCallbackPanel<? extends Serializable> callback) {
        super(id, model);
        this.setCallBackPrevuousPanel(callback);
    }

    public AbstractCallbackPanel<? extends Serializable> getCallBackPrevuousPanel() {
        return callBackPrevuousPanel;
    }

    public void back(final AjaxRequestTarget ajaxTarget) {
        this.getCallBackPrevuousPanel().back(ajaxTarget);
    }

    public Link<Object> getBackLink(final String wicketId) {
        return new Link<Object>(wicketId) {

            private static final long serialVersionUID = 7673747262781072287L;

            @Override
            public void onClick() {
                // NavigableWebPage.this.setResponsePage(NavigableWebPage.this.getPreviousPage());
            }
        };
    }

    public void setCallBackPrevuousPanel(final AbstractCallbackPanel<? extends Serializable> previousPage) {
        this.callBackPrevuousPanel = previousPage;
    }
}
