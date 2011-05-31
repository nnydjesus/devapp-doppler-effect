package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;

import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.presentation.util.CallBack;
import ar.edu.unq.dopplereffect.user.User;

public class LoginPanelSubmitButton extends AjaxButton {

    private static final long serialVersionUID = 1L;

    private LoginPanel loginPanel;

    public LoginPanelSubmitButton(final String id, final LoginPanel loginPanel) {
        super(id);
        this.loginPanel = loginPanel;
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(final LoginPanel loginPanel) {
        // solo porque rompe las bolas el PMD
        this.loginPanel = loginPanel;
    }

    @Override
    protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
        String userName = this.getLoginPanel().getUserIdField().getDefaultModelObjectAsString();
        String password = this.getLoginPanel().getPassField().getDefaultModelObjectAsString();
        if (this.getLoginPanel().getState().equals(StateLogin.LOGIN)) {
            this.getLoginPanel().getService()
                    .login(userName, password, this.loginCallback(target), this.errorCallback());
        } else {
            this.getLoginPanel().getService()
                    .signUp(userName, password, this.registerCallBack(target), this.errorCallback());
        }
    }

    @Override
    protected void onError(final AjaxRequestTarget target, final Form<?> form) {
        target.addComponent(this.getLoginPanel().getFeedbackPanel());
    }

    protected CallBack<User> loginCallback(final AjaxRequestTarget target) {
        return new CallBack<User>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void execute(final User user) {
                LoginPanelSubmitButton.this.getLoginPanel().getCallBack().execute(target, user);
            }
        };
    }

    protected CallBack<Object> registerCallBack(final AjaxRequestTarget target) {
        return new CallBack<Object>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void execute(final Object object) {
                LoginPanelSubmitButton.this.getLoginPanel().gotoLogin();
                target.addComponent(LoginPanelSubmitButton.this.getForm());
            }
        };
    }

    protected CallBack<UserException> errorCallback() {
        return new CallBack<UserException>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void execute(final UserException exception) {
                LoginPanelSubmitButton.this.error(LoginPanelSubmitButton.this.getLocalizer().getString(
                        exception.getMessage(), LoginPanelSubmitButton.this.getLoginPanel()));
            }
        };
    }
}
