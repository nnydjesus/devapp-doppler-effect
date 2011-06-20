package ar.edu.unq.dopplereffect.presentation;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.target.coding.MixedParamHybridUrlCodingStrategy;

public class MounterURL {

    /* ************************ INSTANCE VARIABLES ************************ */

    private WebApplication webApplication;

    /* *************************** CONSTRUCTORS *************************** */

    public MounterURL(final WebApplication webApplication) {
        this.setWebApplication(webApplication);
    }

    /* **************************** ACCESSORS ***************************** */

    public WebApplication getWebApplication() {
        return webApplication;
    }

    public void setWebApplication(final WebApplication webApplication) {
        this.webApplication = webApplication;
    }

    /* **************************** OPERATIONS **************************** */

    /**
     * @param mountPath
     *            Mount path (not empty)
     * @param pageClass
     *            Class of mounted page (not null)
     * @param parameters
     *            The parameter names (not null)
     */
    public void mount(final String mountPath, final Class<? extends WebPage> pageClass, final String... parameters) {
        MixedParamHybridUrlCodingStrategy urls = new MixedParamHybridUrlCodingStrategy(mountPath, pageClass, parameters);
        this.getWebApplication().mount(urls);
    }

    /**
     * @param mountPath
     *            Mount path (not empty)
     * @param pageClass
     *            Class of mounted page (not null)
     * @param redirectOnBookmarkableRequest
     *            Whether after hitting the page with URL in bookmarkable form
     *            it should be redirected to hybrid URL - needed for ajax to
     *            work properly after page refresh
     * @param parameters
     *            The parameter names (not null)
     */
    public void mount(final String mountPath, final Class<? extends WebPage> pageClass,
            final boolean redirectOnBookmarkableRequest, final String... parameters) {
        MixedParamHybridUrlCodingStrategy urls = new MixedParamHybridUrlCodingStrategy(mountPath, pageClass,
                redirectOnBookmarkableRequest, parameters);
        this.getWebApplication().mount(urls);
    }
}