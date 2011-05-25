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
public class StylePage<T> extends WebPage implements Serializable{

    protected final static String BODY = "body";

    protected final static String FOOTER = "footer";

    protected final static String HEADER = "header";

    protected final static String HEADER2 = "header2";

    private Component defaultBody = new Label("body", new Model("Welcome to de dance of death"));

    private Panel ajaxPanel;

    private Component body;

    public StylePage(final Class<Panel> body, final Object... params) {
        this(ReflectionUtils.instanciate(body, params));
    }

    public StylePage(final Component component) {
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
        return new Label(FOOTER, new Model("Footer!!!"));
    }

    protected Component createHeader() {
        return new Label(HEADER, new Model("Doopler"));
    }

    protected Component createHeader2() {
        return new Label(HEADER2, new Model("Effect"));
    }

    public StylePage() {
        this(new Label("body", new Model("Welcome to de dance of death")));
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

}
