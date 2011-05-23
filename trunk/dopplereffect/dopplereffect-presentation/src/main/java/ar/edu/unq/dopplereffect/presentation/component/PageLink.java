package ar.edu.unq.dopplereffect.presentation.component;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import ar.edu.unq.dopplereffect.presentation.pages.basic.WebPageFactory;

public class PageLink extends Link {
    private static final long serialVersionUID = 1L;

    private WebPageFactory pageFactory;

    public PageLink(final String id, final WebPageFactory pageFactory) {
        super(id);
        this.pageFactory = pageFactory;
    }

    public PageLink(final String id, final WebPage page) {
        this(id, new WebPageFactory() {
            private static final long serialVersionUID = 1L;

            @Override
            public WebPage createPage() {
                return page;
            }
        });
    }

    @Override
    public void onClick() {
        this.setResponsePage(pageFactory.createPage());
    }
}