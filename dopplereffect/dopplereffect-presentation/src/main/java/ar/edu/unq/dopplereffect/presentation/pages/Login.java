package ar.edu.unq.dopplereffect.presentation.pages;

import ar.edu.unq.dopplereffect.presentation.panel.LoginPanel;

public class Login extends AbstractWebPage<LoginPanel> {

    public Login() {
        // super(GridPanel.class, BODY);
        super(LoginPanel.class, "body");
        ((LoginPanel) this.getBody()).init();
    }
}