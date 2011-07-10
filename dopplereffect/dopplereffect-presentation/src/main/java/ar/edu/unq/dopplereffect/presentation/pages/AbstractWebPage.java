package ar.edu.unq.dopplereffect.presentation.pages;

import java.io.Serializable;
import java.util.Locale;

import javax.servlet.http.Cookie;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebRequest;

import ar.edu.unq.dopplereffect.mail.LocaleManager;
import ar.edu.unq.dopplereffect.presentation.ExceptionManager;
import ar.edu.unq.dopplereffect.presentation.panel.LanguageSelectorPanel;
import ar.edu.unq.dopplereffect.presentation.panel.LoginStatusPanel;
import ar.edu.unq.dopplereffect.presentation.panel.SimplePanel;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 * Representa la estructura de la pagina principal de la aplicacion.
 */
public class AbstractWebPage<T extends Component> extends WebPage implements Serializable {

    private static final long serialVersionUID = 1L;

    // @formatter:off
    protected static final String
        BODY = "body",
        FOOTER = "footer",
        HEADER = "header",
        SIDEBAR = "sidebar",
        TITLE = "title",
        TITLE2 = "title2";
    // @formatter:on

    /* ************************ INSTANCE VARIABLES ************************ */

    private Component defaultBody = new Label(BODY, new Model<String>("Welcome to de dance of death"));

    private Panel ajaxPanel;

    private Component body;

    private static ExceptionManager managerException;

    // private WebMarkupContainer bodyContainer;

    /* *************************** CONSTRUCTORS *************************** */

    public AbstractWebPage() {
        this(new Label(BODY, new Model<String>("Welcome to de dance of death")));
    }

    public AbstractWebPage(final Class<T> body, final Object... params) {
        this(ReflectionUtils.instanciate(body, params));
    }

    public AbstractWebPage(final Component component) {
        super();
        this.setOutputMarkupId(true);
        this.readCookies();
        managerException = new ExceptionManager(this);
        body = component;
        ajaxPanel = new SimplePanel("body");
        ajaxPanel.add(component);
        ajaxPanel.setOutputMarkupId(true);
        this.makePage();
    }

    protected void makePage() {
        this.add(ajaxPanel);
        this.add(this.createFooter());
        this.add(this.createHeader());
        this.add(this.createTitle());
        this.add(this.createTitle2());
        this.add(this.createSidebar());
        this.add(new LanguageSelectorPanel("language_select"));
    }

    /* **************************** ACCESSORS ***************************** */

    protected Component getBody() {
        return body;
    }

    public void setBody(final Component component) {
        component.setOutputMarkupId(true);
        ajaxPanel.addOrReplace(component);
    }

    public void setDefaultBody() {
        this.setBody(defaultBody);
    }

    public Component getAjaxPanel() {
        return ajaxPanel;
    }

    public void setAjaxPanel(final Panel bodyPanel) {
        this.ajaxPanel = bodyPanel;
    }

    public Component getDefaultBody() {
        return defaultBody;
    }

    public void setDefaultBody(final Component defaultBody) {
        this.defaultBody = defaultBody;
    }

    /* ************************* PRIVATE METHODS ************************** */

    protected Component createSidebar() {
        return new Label(SIDEBAR, new Model<String>("Sidebar"));
    }

    protected Component createFooter() {
        // return new Label(FOOTER, new Model<String>("Footer!!!"));
        return new LoginStatusPanel(FOOTER);
    }

    protected Component createHeader() {
        return new Label(HEADER, new Model<String>("Doppler"));
    }

    protected Component createTitle() {
        return new Label(TITLE, new Model<String>("Doppler"));
    }

    protected Component createTitle2() {
        return new Label(TITLE2, new Model<String>("Effect"));
    }

    private void readCookies() {
        String language = null;
        String country = null;
        String variant = null;

        for (Cookie cookie : ((WebRequest) this.getRequestCycle().getRequest()).getCookies()) {
            if (cookie.getName().equals("locale.language")) {
                language = cookie.getValue();
            } else if (cookie.getName().equals("locale.country")) {
                country = cookie.getValue();
            } else if (cookie.getName().equals("locale.variant")) {
                variant = cookie.getValue();
            }
        }
        if (language != null || country != null | variant != null) {
            Locale locale = new Locale(language, country, variant);
            LocaleManager.getLocaleManager().setLocale(locale);
            this.getSession().setLocale(locale);
        }
    }

    /* ************************* OPERATIONS ************************** */

    public void showError(final Exception exception) {
        ErrorPage errorPage = new ErrorPage();
        errorPage.setTitle(exception.getMessage());
        errorPage.setMessage("A ocurrido un error inesperado. \nLe reconmendamos recargar la pagina");
        this.setResponsePage(errorPage);
    }

    public static ExceptionManager getManagerException() {
        return managerException;
    }
}
