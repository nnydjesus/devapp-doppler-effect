package ar.edu.unq.dopplereffect.presentation.pages;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import ar.edu.unq.dopplereffect.presentation.panel.SimplePanel;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 * Pagina para probar el wicket extend No puede hacer que reflesque por ajax el
 * body
 */
public class StylePage<T extends Component> extends WebPage implements Serializable {
    private static final long serialVersionUID = 1L;

    protected static final String BODY = "body";

    protected static final String FOOTER = "footer";

    protected static final String HEADER = "header";

    protected static final String HEADER2 = "header2";

    private Component defaultBody = new Label("body", new Model<String>("Welcome to de dance of death"));

    private Panel ajaxPanel;

    private Component body;

    public StylePage(final Class<T> body, final Object... params) {
        this(ReflectionUtils.instanciate(body, params));
    }

    public StylePage(final Component component) {
        super();
        body = component;
        ajaxPanel = new SimplePanel(BODY);
        ajaxPanel.add(component);
        ajaxPanel.setOutputMarkupId(true);
        this.add(ajaxPanel);
        this.add(this.createFooter());
        this.add(this.createHeader());
        this.add(this.createHeader2());

        // bodyPanel = component;
        // bodyPanel.setOutputMarkupId(true);
        // this.add(bodyPanel);
    }

    protected Component getBody() {
        return body;
    }

    protected Component createFooter() {
        return new Label(FOOTER, new Model<String>("Footer!!!"));
    }

    protected Component createHeader() {
        return new Label(HEADER, new Model<String>("Doopler"));
    }

    protected Component createHeader2() {
        return new Label(HEADER2, new Model<String>("Effect"));
    }

    public StylePage() {
        this(new Label("body", new Model<String>("Welcome to de dance of death")));
        // this(new EmptyPanel(BODY));
    }

    public void setDefaultBody() {
        this.setBody(defaultBody);
    }

    public void setBody(final Component component) {
        component.setOutputMarkupId(true);
        ajaxPanel.addOrReplace(component);

        // this.addOrReplace(component);
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
