package ar.edu.unq.dopplereffect.presentation.pages;

import org.apache.wicket.authentication.AuthenticatedWebSession;

import ar.edu.unq.dopplereffect.presentation.panel.LoginPanel;

public class Login extends AbstractWebPage<LoginPanel> {

    public Login() {
        // super(GridPanel.class, BODY);
        super(LoginPanel.class, BODY);
        ((LoginPanel) this.getBody()).init();

        this.redirectToHomeIfAlreadyLoggedIn();
    }

    private void redirectToHomeIfAlreadyLoggedIn() {
        if (AuthenticatedWebSession.get().isSignedIn()) {
            this.setResponsePage(new HomePage());
        }
    }
}