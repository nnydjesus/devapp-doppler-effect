package ar.edu.unq.dopplereffect.presentation.pages;

import ar.edu.unq.dopplereffect.presentation.panel.LoginPanel;

public class Login extends StylePage {

    /**
     * Login page constituents are the same as Login.html except that it is made
     * up of equivalent Wicket components
     */

    public Login() {
        super(LoginPanel.class, BODY);
        ((LoginPanel) this.getBody()).init();
    }

}