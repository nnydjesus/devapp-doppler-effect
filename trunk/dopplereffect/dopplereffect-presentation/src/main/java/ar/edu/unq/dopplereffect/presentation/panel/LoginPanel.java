package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.odlabs.wiquery.ui.button.ButtonBehavior;

import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.presentation.App;
import ar.edu.unq.dopplereffect.presentation.Authenticate;
import ar.edu.unq.dopplereffect.presentation.pages.Home;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.CallBack;
import ar.edu.unq.dopplereffect.user.User;

/**
 */
public class LoginPanel extends AbstractPanel<Model<String>> {

    private static final long serialVersionUID = 1L;

    private TextField<String> userIdField;

    private PasswordTextField passField;

    private boolean rememberMe = false;

    private Authenticate service;

    private WebPage dafaultPage;

    private StateLogin state = StateLogin.LOGIN;

    private WebMarkupContainer rememberMeRow;

    private Component submit;

    private Component register;

    private Model<String> submitModel;

    public LoginPanel(final String id) {
        super(id);
        service = App.loginService;
    }

    public void init() {
        this.add(this.createForm("loginForm"));
    }

    @Override
    protected Form<Model<String>> createForm(final String formName) {
        Form<Model<String>> form = new Form<Model<String>>(formName);
        this.setUserIdField(new RequiredTextField<String>("name", new Model<String>("")));
        this.setPassField(new PasswordTextField("password", new Model<String>("")));

        this.setRememberMeRow(new WebMarkupContainer("rememberMeRow"));
        //
        // // Add rememberMe checkbox
        this.getRememberMeRow().add(new CheckBox("rememberMe", new PropertyModel<Boolean>(this, "rememberMe")));

        // Make form values persistent
        this.getUserIdField().setPersistent(rememberMe);

        // Show remember me checkbox?
        this.getRememberMeRow().setVisible(true);

        /* Make sure that password field shows up during page re-render * */

        this.getPassField().setResetPassword(true);

        this.setDafaultPage(new Home());
        form.add(this.getUserIdField());
        form.add(this.getPassField());
        form.add(this.getRememberMeRow());
        form.add(this.setFeedbackPanel(new FeedbackPanel("feedbackPanel")));
        this.setSubmit(this.createSubmitButton().add(new ButtonBehavior()));
        form.add(this.getSubmit());
        this.setRegister(this.createRegisterButton().add(new ButtonBehavior()));
        form.add(this.getRegister());
        return form;
    }

    private AjaxButton createSubmitButton() {
        this.setSubmitModel(new Model<String>("Login"));
        return new AjaxButton("submit", this.getSubmitModel()) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                String userName = LoginPanel.this.getUserIdField().getDefaultModelObjectAsString();
                String password = LoginPanel.this.getPassField().getDefaultModelObjectAsString();
                if (LoginPanel.this.getState().equals(StateLogin.LOGIN)) {
                    LoginPanel.this.getService().login(userName, password, this.loginCallback(target),
                            this.errorCallback());
                } else {
                    LoginPanel.this.getService().signUp(userName, password, this.registerCallBack(target),
                            this.errorCallback());
                }
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                target.addComponent(LoginPanel.this.getFeedbackPanel());
            }

            private CallBack<User> loginCallback(final AjaxRequestTarget target) {
                return new CallBack<User>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void execute(final User user) {
                        LoginPanel.this.getCallBack().execute(target, user);
                    }
                };
            }

            private CallBack<Object> registerCallBack(final AjaxRequestTarget target) {
                return new CallBack<Object>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void execute(final Object o) {
                        LoginPanel.this.setState(StateLogin.LOGIN);
                        LoginPanel.this.getRememberMeRow().setVisible(true);
                        LoginPanel.this.getSubmitModel().setObject("Login");
                        LoginPanel.this.getRegister().setVisible(true);
                        target.addComponent(getForm());
                    }
                };
            }

            private CallBack<UserException> errorCallback() {
                return new CallBack<UserException>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void execute(final UserException e) {
                        error(getLocalizer().getString(e.getMessage(), LoginPanel.this));
                    }
                };
            }

        };
    }

    private AjaxButton createRegisterButton() {
        return new AjaxButton("register", new Model<String>("Registrarme")) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                LoginPanel.this.setState(StateLogin.REGISTER);
                LoginPanel.this.getRememberMeRow().setVisible(false);
                LoginPanel.this.getRegister().setVisible(false);
                LoginPanel.this.getSubmitModel().setObject("Aceptar");
                target.addComponent(form);
            }
        };
    }

    protected AjaxCallBack<Object> getCallBack() {
        return new AjaxCallBack<Object>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void execute(final AjaxRequestTarget ajaxTarget, final Object component) {
                LoginPanel.this.setResponsePage(LoginPanel.this.getDafaultPage());
            }
        };
    }

    public void setService(final Authenticate service) {
        this.service = service;
    }

    public Authenticate getService() {
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

    public void setRememberMe(final boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMeRow(final WebMarkupContainer rememberMeRow) {
        this.rememberMeRow = rememberMeRow;
    }

    public WebMarkupContainer getRememberMeRow() {
        return rememberMeRow;
    }

    public void setState(final StateLogin state) {
        this.state = state;
    }

    public StateLogin getState() {
        return state;
    }

    public void setSubmitModel(final Model<String> submitModel) {
        this.submitModel = submitModel;
    }

    public Model<String> getSubmitModel() {
        return submitModel;
    }

    public void setRegister(final Component register) {
        this.register = register;
    }

    public Component getRegister() {
        return register;
    }

    public void setSubmit(final Component submit) {
        this.submit = submit;
    }

    public Component getSubmit() {
        return submit;
    }

}