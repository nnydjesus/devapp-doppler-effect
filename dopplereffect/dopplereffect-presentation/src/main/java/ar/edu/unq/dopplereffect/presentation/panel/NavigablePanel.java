package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.markup.html.link.Link;

import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;

/**
 * Panel que recuerda el panel anterior, permitiendo asi la navegacion.
 */
public class NavigablePanel<T> extends AbstractCallbackPanel<T> {

    private static final long serialVersionUID = 1L;

    public NavigablePanel(final String id, final T model) {
        super(id, model);
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
}
