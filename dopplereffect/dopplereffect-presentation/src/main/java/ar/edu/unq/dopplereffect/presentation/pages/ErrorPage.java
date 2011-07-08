package ar.edu.unq.dopplereffect.presentation.pages;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;

import ar.edu.unq.dopplereffect.presentation.components.CustomComponent;

/**
 */
public class ErrorPage extends AbstractWebPage<Component> {

    private String message = "Page Not Found";

    private String title = "Error";

    public ErrorPage() {
        super();
        this.setBody(new Label("body", new PropertyModel<String>(this, "title")));
        this.add(new Label("message", new PropertyModel<String>(this, "message")));
        this.add(CustomComponent.addButtonSking(new AjaxLink<String>("acept") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                this.setResponsePage(HomePage.class);
            }

        }));

    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
