package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import ar.edu.unq.dopplereffect.presentation.pages.Login;

/**
 * Muestra si estamos logueados, y un link para cerrar sesion.
 */
public class LoginStatusPanel extends Panel {

    private static final long serialVersionUID = 7892259147524514251L;

    public LoginStatusPanel(final String id) {
        super(id);
        this.setOutputMarkupId(true);
        Link<String> logOutLink = new Link<String>("sign_out_link") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                AuthenticatedWebSession.get().signOut();
                this.setResponsePage(Login.class);
            }

        };
        AuthenticatedWebSession session = AuthenticatedWebSession.get();
        String text = this.getLocalizer().getString("login.not_logged_in", this);
        logOutLink.setVisible(false);
        if (session.isSignedIn()) {
            text = this.getLocalizer().getString("login.logged_in", this);
            logOutLink.setVisible(true);
        }
        this.add(new Label("login_text", new Model<String>(text)));
        this.add(logOutLink);
    }

}
