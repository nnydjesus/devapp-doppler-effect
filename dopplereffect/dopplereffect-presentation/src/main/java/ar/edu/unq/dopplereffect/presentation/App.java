package ar.edu.unq.dopplereffect.presentation;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequestCycleProcessor;
import org.apache.wicket.protocol.http.request.CryptedUrlWebRequestCodingStrategy;
import org.apache.wicket.protocol.http.request.WebRequestCodingStrategy;
import org.apache.wicket.request.IRequestCodingStrategy;
import org.apache.wicket.request.IRequestCycleProcessor;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import ar.edu.unq.dopplereffect.presentation.pages.Login;

public class App extends WebApplication {// implements IThemableApplication {

    // para tener datos en memoria.... per solo se quiere
    // ingresar esos datos cuando levanta el jetty
    private static boolean isCreate = false;

    @Override
    protected void init() {
        super.init();
        this.addResources();
        this.addComponentInstantiationListener(new SpringComponentInjector(this));
    }

    private void addResources() {
        this.getResourceSettings().addResourceFolder("i18n");
        this.getResourceSettings().addResourceFolder("pages");
        this.getResourceSettings().addResourceFolder("panel");
        this.getResourceSettings().addResourceFolder("Images");
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
    public Session newSession(final Request request, final Response response) {
        return new WicketWebSession(request);
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return Login.class;
    }

    public String getContextPath() {
        return this.getServletContext().getContextPath();
    }

    public static void setCreate(final boolean isCreate) {
        App.isCreate = isCreate;
    }

    public static boolean isCreate() {
        return isCreate;
    }

    // @Override
    // public ResourceReference getTheme(final Session session) {
    // return RedmondTheme.getInstance().getTheme();
    // }
}