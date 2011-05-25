package ar.edu.unq.dopplereffect.presentation.panel.utils;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

import ar.edu.unq.dopplereffect.presentation.pages.basic.WebComponentFactory;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

public class PageLink extends Link<Object> {
    private static final long serialVersionUID = 1L;

    private WebComponentFactory<Component> pageFactory;

    public PageLink(final String id, final WebComponentFactory<Component> pageFactory) {
        super(id);
        this.setPageFactory(pageFactory);
    }

    public PageLink(final String id, final Class<? extends Page> page) {
        this(id, ReflectionUtils.instanciate(page));

    }

    public PageLink(final String id, final Page page) {
        this(id, new WebComponentFactory<Component>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Page createPage() {
                return page;
            }
        });
    }

    @Override
    public void onClick() {
        this.setResponsePage((Page) this.getPageFactory().createPage());
    }

    public void setPageFactory(final WebComponentFactory<Component> pageFactory) {
        this.pageFactory = pageFactory;
    }

    public WebComponentFactory<Component> getPageFactory() {
        return pageFactory;
    }
}