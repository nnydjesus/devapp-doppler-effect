package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import ar.edu.unq.dopplereffect.presentation.pages.Home;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.util.CallBack;
import ar.edu.unq.dopplereffect.service.LoginService;
import ar.edu.unq.dopplereffect.user.User;
import ar.edu.unq.tpi.util.common.HashUtils;

/**
 */
public class LoginPanel extends AbstractPanel<Model<String>> {

    private static final long serialVersionUID = 1L;

    private TextField<String> userIdField;

    private PasswordTextField passField;

    private LoginService service;

    private WebPage dafaultPage;

    public LoginPanel(final String id) {
        super(id);
        this.setService(new LoginService());
    }

    public void init() {
        this.add(this.createForm("loginForm"));
    }

    @Override
    protected Form<Model<String>> createForm(final String formName) {

        Form<Model<String>> form = new Form<Model<String>>("loginForm");
        this.setUserIdField(new RequiredTextField<String>("name", new Model<String>("")));
        this.setPassField(new PasswordTextField("password", new Model<String>("")));

        /* Make sure that password field shows up during page re-render * */

        this.getPassField().setResetPassword(true);

        this.setDafaultPage(new Home(this.getPage()));
        form.add(this.getUserIdField());
        form.add(this.getPassField());
        form.add(this.setFeedbackPanel(new FeedbackPanel("feedbackPanel")));
        form.add(this.createSubmitButton());
        return form;
    }

    private AjaxButton createSubmitButton() {
        return new AjaxButton("submit") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                String userName = LoginPanel.this.getUserIdField().getDefaultModelObjectAsString();
                String password = LoginPanel.this.getPassField().getDefaultModelObjectAsString();
                HashUtils.hash(password);

                User user = LoginPanel.this.getService().login(userName, password);
                if (LoginPanel.this.getCallBack() != null) {
                    LoginPanel.this.getCallBack().execute(user);
                }
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                target.add(LoginPanel.this.getFeedbackPanel());
            }
        };
    }

    protected CallBack<Object> getCallBack() {
        return new CallBack<Object>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void execute(final Object args) {
                LoginPanel.this.setResponsePage(LoginPanel.this.getDafaultPage());
            }
        };
    }

    public void setService(final LoginService service) {
        this.service = service;
    }

    public LoginService getService() {
        return service;
    }

    public void setPassField(final PasswordTextField passField) {
        this.passField = passField;
    }

    public PasswordTextField getPassField() {
        return passField;
    }

    public void setUserIdField(final TextField<String> userIdField) {
        this.userIdField = userIdField;
    }

    public TextField<String> getUserIdField() {
        return userIdField;
    }

    public void setDafaultPage(final WebPage dafaultPage) {
        this.dafaultPage = dafaultPage;
    }

    public WebPage getDafaultPage() {
        return dafaultPage;
    }

}
