package ar.edu.unq.dopplereffect.presentation.login;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.Model;

import ar.edu.unq.dopplereffect.presentation.util.CallBack;
import ar.edu.unq.dopplereffect.service.LoginService;
import ar.edu.unq.dopplereffect.user.User;

/**
 * TODO: description
 */
public class LoginForm extends Form {
    private static final long serialVersionUID = 1L;

    private WebPage parent;

    private CallBack callBack;

    private RequiredTextField<String> userIdField;

    private PasswordTextField passField;

    LoginService service;

    public LoginForm(final String id, final WebPage parent) {
        super(id);
        this.parent = parent;
        service = new LoginService();
        userIdField = new RequiredTextField<String>("userId", new Model(""));
        passField = new PasswordTextField("password", new Model(""));

        /* Make sure that password field shows up during page re-render * */

        passField.setResetPassword(false);
        this.add(userIdField);
        this.add(passField);
    }

    // @Override
    // protected void onSubmit() {
    // AuthenticatedWebSession session = AuthenticatedWebSession.get();
    // if(session.signIn(username, password)) {
    // setDefaultResponsePageIfNecessary();
    // } else {
    // error(getString("login.failed"));
    // }
    // }

    private void setDefaultResponsePageIfNecessary() {
        if (!this.continueToOriginalDestination()) {
            this.setResponsePage(this.getApplication().getHomePage());
        }
    }

    @Override
    public void onSubmit() {
        User user = service.login(userIdField.getDefaultModelObjectAsString(),
                passField.getDefaultModelObjectAsString());
        if (this.getCallBack() != null) {
            this.getCallBack().execute(user);
        }
    }

    public void setCallBack(final CallBack callBack) {
        this.callBack = callBack;
    }

    public CallBack getCallBack() {
        return callBack;
    }

}