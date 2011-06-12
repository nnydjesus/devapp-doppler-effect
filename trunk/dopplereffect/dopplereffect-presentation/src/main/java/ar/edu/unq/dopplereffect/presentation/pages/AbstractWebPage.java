package ar.edu.unq.dopplereffect.presentation.pages;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import ar.edu.unq.dopplereffect.presentation.panel.LanguageSelectorPanel;
import ar.edu.unq.dopplereffect.presentation.panel.SimplePanel;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 * Pagina para probar el wicket extend No puede hacer que reflesque por ajax el
 * body
 */
public class AbstractWebPage<T extends Component> extends WebPage implements Serializable {
    private static final long serialVersionUID = 1L;

    protected static final String BODY = "body";

    protected static final String FOOTER = "footer";

    protected static final String HEADER = "header";

    protected static final String SIDEBAR = "sidebar";

    protected static final String TITLE = "title";

    protected static final String TITLE2 = "title2";

    private Component defaultBody = new Label("body", new Model<String>("Welcome to de dance of death"));

    private Panel ajaxPanel;

    private Component body;

    public AbstractWebPage(final Class<T> body, final Object... params) {
        this(ReflectionUtils.instanciate(body, params));
    }

    public AbstractWebPage(final Component component) {
        super();
        body = component;
        ajaxPanel = new SimplePanel(BODY);
        ajaxPanel.add(component);
        ajaxPanel.setOutputMarkupId(true);
        this.add(ajaxPanel);
        this.add(this.createFooter());
        this.add(this.createHeader());
        this.add(this.createTitle());
        this.add(this.createTitle2());
        this.add(this.createSidebar());
        this.add(new LanguageSelectorPanel("language_select"));

    }

    protected Component getBody() {
        return body;
    }

    protected Component createSidebar() {
        return new Label(SIDEBAR, new Model<String>("Sidebar"));
    }

    protected Component createFooter() {
        return new Label(FOOTER, new Model<String>("Footer!!!"));
    }

    protected Component createHeader() {
        return new Label(HEADER, new Model<String>("Doopler"));
    }

    protected Component createTitle() {
        return new Label(TITLE, new Model<String>("Doopler"));
    }

    protected Component createTitle2() {
        return new Label(TITLE2, new Model<String>("Effect"));
    }

    public AbstractWebPage() {
        this(new Label(BODY, new Model<String>("Welcome to de dance of death")));
    }

    public void setDefaultBody() {
        this.setBody(defaultBody);
    }

    public void setBody(final Component component) {
        component.setOutputMarkupId(true);
        ajaxPanel.addOrReplace(component);
    }

    public void setAjaxPanel(final Panel bodyPanel) {
        this.ajaxPanel = bodyPanel;
    }

    public Component getAjaxPanel() {
        return ajaxPanel;
    }

    public void setDefaultBody(final Component defaultBody) {
        this.defaultBody = defaultBody;
    }

    public Component getDefaultBody() {
        return defaultBody;
    }

}
