package ar.edu.unq.dopplereffect.presentation.pages;

import org.apache.wicket.markup.html.WebPage;

import ar.edu.unq.dopplereffect.presentation.login.LoginForm;
import ar.edu.unq.dopplereffect.presentation.util.CallBack;

public class Login extends StylePage {

    /**
     * Login page constituents are the same as Login.html except that it is made
     * up of equivalent Wicket components
     */

    private LoginForm form;

    private WebPage pageXXX; // no se que ponerle

    public Login() {
        form = new LoginForm("loginForm", this);
        form.setCallBack(this.getCallBack());
        pageXXX = new MainPage();
        this.add(form);
    }

    private CallBack getCallBack() {
        return new CallBack() {

            @Override
            public void execute(final Object... args) {
                Login.this.setResponsePage(pageXXX);
            }
        };
    }
}