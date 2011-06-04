package ar.edu.unq.dopplereffect.presentation.panel;

import java.util.Locale;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
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
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.odlabs.wiquery.ui.button.ButtonBehavior;

import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.presentation.Authenticate;
import ar.edu.unq.dopplereffect.presentation.pages.HomePage;
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

    @SpringBean(name = "authenticate")
    private Authenticate service;

    private WebPage dafaultPage;

    private StateLogin state = StateLogin.LOGIN;

    private WebMarkupContainer rememberMeRow;

    private Component submit;

    private Component register;

    private StringResourceModel loginSubmitModel;

    private StringResourceModel registerSubmitModel;

    private StringResourceModel loginRegisterModel;

    private StringResourceModel registerBackmModel;

    private AjaxButton submitButton;

    private AjaxLink<String> registerButton;

    private ButtonBehavior registerBehavior;

    public LoginPanel(final String id) {
        super(id);
    }

    public void init() {
        this.setLoginSubmitModel(this.createLocaleResources("login.submit"));
        this.setRegisterSubmitModel(this.createLocaleResources("register.submit"));
        this.setLoginRegisterModel(this.createLocaleResources("login.register"));
        this.setRegisterBackmmodel(this.createLocaleResources("register.back"));
        this.add(this.createForm("loginForm"));
    }

    protected StringResourceModel createLocaleResources(final String key) {
        return new StringResourceModel(key, new Model<Locale>(this.getLocale()));
    }

    @Override
    protected Form<Model<String>> createForm(final String formName) {
        Form<Model<String>> form = new Form<Model<String>>(formName);
        this.setUserIdField(new RequiredTextField<String>("name", new Model<String>("")));
        this.setPassField(new PasswordTextField("password", new Model<String>("")));

        this.setRememberMeRow(new WebMarkupContainer("rememberMeRow"));
        // // Add rememberMe checkbox
        this.getRememberMeRow().add(new CheckBox("rememberMe", new PropertyModel<Boolean>(this, "rememberMe")));
        // Make form values persistent
        this.getUserIdField().setPersistent(rememberMe);

        this.getRememberMeRow().setVisible(true);

        /* Make sure that password field shows up during page re-render * */
        this.getPassField().setResetPassword(true);

        this.setSubmitButton(this.createSubmitButton());
        this.setRegisterButton(this.createRegisterButton());
        this.setSubmit(this.getSubmitButton().add(new ButtonBehavior()));
        this.setRegisterBehavior(new ButtonBehavior().setLabel(this.getLoginRegisterModel()));
        this.setRegister(this.getRegisterButton().add(this.getRegisterBehavior()));

        this.setDafaultPage(new HomePage());
        form.add(this.getUserIdField());
        form.add(this.getPassField());
        form.add(this.getRememberMeRow());
        form.add(this.setFeedbackPanel(new FeedbackPanel("feedbackPanel")));
        form.add(this.getSubmit());
        form.add(this.getRegister());
        return form;
    }

    protected AjaxButton createSubmitButton() {
        return new AjaxButton("submit") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                String userName = LoginPanel.this.getUserIdField().getDefaultModelObjectAsString();
                String password = LoginPanel.this.getPassField().getDefaultModelObjectAsString();
                LoginPanel.this.getState().submit(userName, password, LoginPanel.this, target);
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                target.addComponent(LoginPanel.this.getFeedbackPanel());
            }

        };
    }

    protected AjaxLink<String> createRegisterButton() {
        return new AjaxLink<String>("register", this.getRegisterSubmitModel()) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                LoginPanel.this.getState().onLink(LoginPanel.this);
                target.addComponent(LoginPanel.this);
            }
        };
    }

    public void submitRegister(final String userName, final String password, final AjaxRequestTarget target) {
        this.getService().signUp(userName, password, this.registerCallBack(target), this.errorCallback());
    }

    public void submitLogin(final String userName, final String password, final AjaxRequestTarget target) {
        this.getService().login(userName, password, this.loginCallback(target), this.errorCallback());
    }

    protected CallBack<User> loginCallback(final AjaxRequestTarget target) {
        return new CallBack<User>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void execute(final User user) {
                LoginPanel.this.getCallBack().execute(target, user);
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

    protected CallBack<UserException> errorCallback() {
        return new CallBack<UserException>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void execute(final UserException exception) {
                LoginPanel.this
                        .error(LoginPanel.this.getLocalizer().getString(exception.getMessage(), LoginPanel.this));
            }
        };
    }

    protected CallBack<Object> registerCallBack(final AjaxRequestTarget target) {
        return new CallBack<Object>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void execute(final Object object) {
                LoginPanel.this.gotoLogin();
                target.addComponent(LoginPanel.this);
            }
        };
    }

    protected void gotoLogin() {
        this.setState(StateLogin.LOGIN);
        this.getRememberMeRow().setVisible(true);
        this.getSubmitButton().setModel(this.getLoginSubmitModel());
        this.getRegisterBehavior().setLabel(this.getLoginRegisterModel());
    }

    protected void gotoRegister() {
        LoginPanel.this.setState(StateLogin.REGISTER);
        LoginPanel.this.getRememberMeRow().setVisible(false);
        this.getSubmitButton().setModel(this.getRegisterSubmitModel());
        this.getRegisterBehavior().setLabel(this.getRegisterBackModel());
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

    public void setLoginSubmitModel(final StringResourceModel loginSubmitModel) {
        this.loginSubmitModel = loginSubmitModel;
    }

    public StringResourceModel getLoginSubmitModel() {
        return loginSubmitModel;
    }

    public void setRegisterSubmitModel(final StringResourceModel registerSubmitModel) {
        this.registerSubmitModel = registerSubmitModel;
    }

    public StringResourceModel getRegisterSubmitModel() {
        return registerSubmitModel;
    }

    public void setLoginRegisterModel(final StringResourceModel loginregisterModel) {
        loginRegisterModel = loginregisterModel;
    }

    public StringResourceModel getLoginRegisterModel() {
        return loginRegisterModel;
    }

    public void setRegisterBackmmodel(final StringResourceModel registerBackmmodel) {
        registerBackmModel = registerBackmmodel;
    }

    public StringResourceModel getRegisterBackModel() {
        return registerBackmModel;
    }

    public void setRegisterBehavior(final ButtonBehavior registerBehavior) {
        this.registerBehavior = registerBehavior;
    }

    public ButtonBehavior getRegisterBehavior() {
        return registerBehavior;
    }

    public void setRegisterButton(final AjaxLink<String> registerButton) {
        this.registerButton = registerButton;
    }

    public AjaxLink<String> getRegisterButton() {
        return registerButton;
    }

    public void setSubmitButton(final AjaxButton submitButton) {
        this.submitButton = submitButton;
    }

    public AjaxButton getSubmitButton() {
        return submitButton;
    }

}
