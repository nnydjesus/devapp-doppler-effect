package ar.edu.unq.dopplereffect.presentation;

import org.apache.wicket.Page;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebRequestCycleProcessor;
import org.apache.wicket.protocol.http.request.CryptedUrlWebRequestCodingStrategy;
import org.apache.wicket.protocol.http.request.WebRequestCodingStrategy;
import org.apache.wicket.request.IRequestCodingStrategy;
import org.apache.wicket.request.IRequestCycleProcessor;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import ar.edu.unq.dopplereffect.presentation.pages.HomePage;
import ar.edu.unq.dopplereffect.presentation.pages.Login;

public class App extends AuthenticatedWebApplication {// implements
                                                      // IThemableApplication {

    // para tener datos en memoria.... per solo se quiere
    // ingresar esos datos cuando levanta el jetty
    private static boolean create = false;

    @Override
    protected void init() {
        super.init();
        this.addComponentInstantiationListener(new SpringComponentInjector(this));
        this.addResources();
        this.mounterURLs();
        this.addComponentInstantiationListener(new SpringComponentInjector(this));
    }

    private void mounterURLs() {
        MounterURL aMounterURL = new MounterURL(this);
        aMounterURL.mount("autenticate", Login.class, "");
        aMounterURL.mount("home", HomePage.class, "");
    }

    private void addResources() {
        this.getResourceSettings().addResourceFolder("Images");
        this.getResourceSettings().addResourceFolder("i18n");
        this.getResourceSettings().addResourceFolder("pages");
        this.getResourceSettings().addResourceFolder("components");
        this.getResourceSettings().addResourceFolder("theme");
    }

    /**
     * @see org.apache.wicket.protocol.http.WebApplication#newRequestCycleProcessor()
     */
    @Override
    protected IRequestCycleProcessor newRequestCycleProcessor() {
        return new WebRequestCycleProcessor() {
            @Override
            protected IRequestCodingStrategy newRequestCodingStrategy() {
                return new CryptedUrlWebRequestCodingStrategy(new WebRequestCodingStrategy());
            }
        };
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return Login.class;
    }

    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return WicketWebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return Login.class;
    }

    public String getContextPath() {
        return this.getServletContext().getContextPath();
    }

    public static void setCreate(final boolean isCreate) {
        App.create = isCreate;
    }

    public static boolean isCreate() {
        return create;
    }

    // @Override
    // public ResourceReference getTheme(final Session session) {
    // return LeFrogTheme.getInstance().getTheme();
    // }
}