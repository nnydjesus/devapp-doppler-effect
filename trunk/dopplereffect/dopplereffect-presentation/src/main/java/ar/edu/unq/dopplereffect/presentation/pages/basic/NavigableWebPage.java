package ar.edu.unq.dopplereffect.presentation.pages.basic;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

/**
 * Pagina que recuerda la pagina anterior, permitiendo asi la navegacion.
 */
public class NavigableWebPage<T> extends AbstractWebPage<T> {

    private WebPage previousPage;

    public NavigableWebPage(final T model, final WebPage previousPage) {
        super(model);
        this.previousPage = previousPage;
    }

    public WebPage getPreviousPage() {
        return previousPage;
    }

    public Link<Object> getBackLink(final String wicketId) {
        return new Link<Object>(wicketId) {

            private static final long serialVersionUID = 7673747262781072287L;

            @Override
            public void onClick() {
                NavigableWebPage.this.setResponsePage(NavigableWebPage.this.getPreviousPage());
            }
        };
    }
}
