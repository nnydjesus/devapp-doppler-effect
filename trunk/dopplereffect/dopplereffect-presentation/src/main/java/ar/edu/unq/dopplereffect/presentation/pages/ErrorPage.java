package ar.edu.unq.dopplereffect.presentation.pages;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;

/**
 */
public class ErrorPage extends AbstractWebPage<Component> {

    public ErrorPage() {
        this.setBody(new Label("body", "Page Not Found!"));

    }
}
