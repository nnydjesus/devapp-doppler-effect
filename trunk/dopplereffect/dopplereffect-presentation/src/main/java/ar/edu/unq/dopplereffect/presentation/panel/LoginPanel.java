package ar.edu.unq.dopplereffect.presentation.panel;

import java.util.Locale;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
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
import ar.edu.unq.dopplereffect.presentation.pages.HomePage;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.CallBackObject;
import ar.edu.unq.dopplereffect.service.LoginService;
import ar.edu.unq.dopplereffect.user.User;
import ar.edu.unq.tpi.util.common.HashUtils;

import com.wiquery.plugin.watermark.TextFieldWatermarkBehaviour;

/**
 * Panel que se encarga de mostrar todo el proceso de identificacion y
 * autenticacion.
 */
public class LoginPanel extends AbstractPanel<Model<String>> {

    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    @SpringBean(name = "service.user")
    private LoginService service;

    private TextField<String> userIdField;

    private TextField<String> emailField;

    private PasswordTextField passField;

    private boolean rememberMe = false;

    private WebPage defaultPage;

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

    /* *************************** CONSTRUCTORS *************************** */

    public LoginPanel(final String id) {
        super(id);
    }

    /* **************************** ACCESSORS ***************************** */

    public LoginService getService() {
        return service;
    }

    public void setService(final LoginService service) {
        this.service = service;
    }

    public PasswordTextField getPassField() {
        return passField;
    }

    public void setPassField(final PasswordTextField passField) {
        this.passField = passField;
    }

    public TextField<String> getUserIdField() {
        return userIdField;
    }

    public void setUserIdField(final TextField<String> userIdField) {
        this.userIdField = userIdField;
    }

    public WebPage getDefaultPage() {
        return defaultPage;
    }

    public void setDefaultPage(final WebPage defaultPage) {
        this.defaultPage = defaultPage;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(final boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public WebMarkupContainer getRememberMeRow() {
        return rememberMeRow;
    }

    public void setRememberMeRow(final WebMarkupContainer rememberMeRow) {
        this.rememberMeRow = rememberMeRow;
    }

    public StateLogin getState() {
        return state;
    }

    public void setState(final StateLogin state) {
        this.state = state;
    }

    public Component getRegister() {
        return register;
    }

    public void setRegister(final Component register) {
        this.register = register;
    }

    public Component getSubmit() {
        return submit;
    }

    public void setSubmit(final Component submit) {
        this.submit = submit;
    }

    public StringResourceModel getLoginSubmitModel() {
        return loginSubmitModel;
    }

    public void setLoginSubmitModel(final StringResourceModel loginSubmitModel) {
        this.loginSubmitModel = loginSubmitModel;
    }

    public StringResourceModel getRegisterSubmitModel() {
        return registerSubmitModel;
    }

    public void setRegisterSubmitModel(final StringResourceModel registerSubmitModel) {
        this.registerSubmitModel = registerSubmitModel;
    }

    public StringResourceModel getLoginRegisterModel() {
        return loginRegisterModel;
    }

    public void setLoginRegisterModel(final StringResourceModel loginregisterModel) {
        loginRegisterModel = loginregisterModel;
    }

    public StringResourceModel getRegisterBackModel() {
        return this.getRegisterBackmModel();
    }

    public void setRegisterBackmmodel(final StringResourceModel registerBackmmodel) {
        this.setRegisterBackmModel(registerBackmmodel);
    }

    public ButtonBehavior getRegisterBehavior() {
        return registerBehavior;
    }

    public void setRegisterBehavior(final ButtonBehavior registerBehavior) {
        this.registerBehavior = registerBehavior;
    }

    public AjaxLink<String> getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(final AjaxLink<String> registerButton) {
        this.registerButton = registerButton;
    }

    public AjaxButton getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(final AjaxButton submitButton) {
        this.submitButton = submitButton;
    }

    public void setEmailField(final TextField<String> emailField) {
        this.emailField = emailField;
    }

    public TextField<String> getEmailField() {
        return emailField;
    }

    public StringResourceModel getRegisterBackmModel() {
        return registerBackmModel;
    }

    public void setRegisterBackmModel(final StringResourceModel registerBackmModel) {
        this.registerBackmModel = registerBackmModel;
    }

    /* **************************** OPERATIONS **************************** */

    public void init() {
        this.setLoginSubmitModel(this.createLocaleResources("login.submit"));
        this.setRegisterSubmitModel(this.createLocaleResources("register.submit"));
        this.setLoginRegisterModel(this.createLocaleResources("login.register"));
        this.setRegisterBackmmodel(this.createLocaleResources("register.back"));
        this.add(this.createForm("loginForm"));
        this.gotoLogin();
    }

    protected StringResourceModel createLocaleResources(final String key) {
        return new StringResourceModel(key, new Model<Locale>(this.getLocale()));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Form<Model<String>> createForm(final String formName) {
        Form<Model<String>> form = new Form<Model<String>>(formName);
        this.setUserIdField((TextField<String>) new RequiredTextField<String>("name", new Model<String>(""))
                .add(new TextFieldWatermarkBehaviour("User name")));
        this.setPassField((PasswordTextField) new PasswordTextField("password", new Model<String>(""))
                .add(new TextFieldWatermarkBehaviour("Password")));

        emailField = (TextField<String>) new RequiredTextField<String>("email", new Model<String>(""))
                .add(new TextFieldWatermarkBehaviour("email"));

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
        this.setSubmit(this.getSubmitButton().add(new ButtonBehavior().setLabel(this.getLoginSubmitModel())));
        this.setRegisterBehavior(new ButtonBehavior().setLabel(this.getLoginRegisterModel()));
        this.setRegister(this.getRegisterButton().add(this.getRegisterBehavior()));

        this.setDefaultPage(new HomePage());
        form.add(this.getUserIdField());
        form.add(this.getPassField());
        form.add(this.getRememberMeRow());
        form.add(this.setFeedbackPanel(new FeedbackPanel("feedbackPanel")));
        form.add(this.getSubmit());
        form.add(this.getEmailField());
        form.add(this.getRegister());
        form.add(new Label("labelEmal"));
        return form;
    }

    protected AjaxButton createSubmitButton() {
        return new AjaxButton("submit") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                String userName = LoginPanel.this.getUserIdField().getDefaultModelObjectAsString();
                String password = LoginPanel.this.getPassField().getDefaultModelObjectAsString();
                String email = LoginPanel.this.getEmailField().getDefaultModelObjectAsString();
                LoginPanel.this.getState().submit(userName, password, email, LoginPanel.this, target);
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

    public void submitRegister(final String userName, final String password, final String email,
            final AjaxRequestTarget target) {
        try {
            this.getService().signUpUser(userName, HashUtils.hash(password), email);
            this.registerCallBack(target).execute(null);
        } catch (UserException e) {
            this.errorCallback(target).execute(e);
        }
    }

    public void submitLogin(final String username, final String password, final AjaxRequestTarget target) {
        AuthenticatedWebSession session = AuthenticatedWebSession.get();
        if (session.signIn(username, password)) {
            this.loginCallback(target).execute(null);
        } else {
            this.errorCallback(target).execute(new UserException("exception.loginFailed"));
        }
    }

    protected CallBackObject<User> loginCallback(final AjaxRequestTarget target) {
        return new CallBackObject<User>() {
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
                LoginPanel.this.setResponsePage(LoginPanel.this.getDefaultPage());
            }
        };
    }

    protected CallBackObject<UserException> errorCallback(final AjaxRequestTarget target) {
        return new CallBackObject<UserException>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void execute(final UserException exception) {
                LoginPanel.this
                        .error(LoginPanel.this.getLocalizer().getString(exception.getMessage(), LoginPanel.this));
                target.addComponent(LoginPanel.this.getFeedbackPanel());
            }
        };
    }

    protected CallBackObject<Object> registerCallBack(final AjaxRequestTarget target) {
        return new CallBackObject<Object>() {
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
        emailField.setVisible(false);
        this.getSubmitButton().setModel(this.getLoginSubmitModel());
        this.getRegisterBehavior().setLabel(this.getLoginRegisterModel());
    }

    protected void gotoRegister() {
        LoginPanel.this.setState(StateLogin.REGISTER);
        LoginPanel.this.getRememberMeRow().setVisible(false);
        emailField.setVisible(true);
        this.getSubmitButton().setModel(this.getRegisterSubmitModel());
        this.getRegisterBehavior().setLabel(this.getRegisterBackModel());
    }
}
