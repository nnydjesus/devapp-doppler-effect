package ar.edu.unq.dopplereffect.presentation.panel;

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
import ar.edu.unq.dopplereffect.presentation.Authenticate;
import ar.edu.unq.dopplereffect.presentation.pages.HomePage;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.CallBack;
import ar.edu.unq.dopplereffect.user.User;

/**
 */
public class RegistrationPanel extends AbstractPanel<Model<String>> {

    private static final long serialVersionUID = 1L;

    private TextField<String> userIdField;

    private PasswordTextField passField;

    private static boolean rememberMe = false;

    private Authenticate service;

    private WebPage dafaultPage;

    public RegistrationPanel(final String id) {
        super(id);
    }

    public void init() {
        this.add(this.createForm("loginForm"));
    }

    @Override
    protected Form<Model<String>> createForm(final String formName) {
        Form<Model<String>> form = new Form<Model<String>>(formName);
        this.setUserIdField(new RequiredTextField<String>("name", new Model<String>("")));
        this.setPassField(new PasswordTextField("password", new Model<String>("")));

        WebMarkupContainer rememberMeRow = new WebMarkupContainer("rememberMeRow");
        //
        // // Add rememberMe checkbox
        rememberMeRow.add(new CheckBox("rememberMe", new PropertyModel<Boolean>(this, "rememberMe")));

        // Make form values persistent
        this.getUserIdField().setPersistent(rememberMe);

        // Show remember me checkbox?
        rememberMeRow.setVisible(true);

        /* Make sure that password field shows up during page re-render * */

        this.getPassField().setResetPassword(true);

        this.setDafaultPage(new HomePage());
        form.add(this.getUserIdField());
        form.add(this.getPassField());
        form.add(rememberMeRow);
        form.add(this.setFeedbackPanel(new FeedbackPanel("feedbackPanel")));
        form.add(this.createSubmitButton().add(new ButtonBehavior()));
        return form;
    }

    private AjaxButton createSubmitButton() {
        return new AjaxButton("submit") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                String userName = RegistrationPanel.this.getUserIdField().getDefaultModelObjectAsString();
                String password = RegistrationPanel.this.getPassField().getDefaultModelObjectAsString();
                RegistrationPanel.this.getService().login(userName, password, this.loginCallback(target),
                        this.errorCallback());
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                target.addComponent(RegistrationPanel.this.getFeedbackPanel());
            }

            private CallBack<User> loginCallback(final AjaxRequestTarget target) {
                return new CallBack<User>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void execute(final User user) {
                        RegistrationPanel.this.getCallBack().execute(target, user);
                    }
                };
            }

            private CallBack<UserException> errorCallback() {
                return new CallBack<UserException>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void execute(final UserException exception) {
                        error(getLocalizer().getString(exception.getMessage(), RegistrationPanel.this));
                    }
                };
            }

        };
    }

    protected AjaxCallBack<Object> getCallBack() {
        return new AjaxCallBack<Object>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void execute(final AjaxRequestTarget ajaxTarget, final Object component) {
                RegistrationPanel.this.setResponsePage(RegistrationPanel.this.getDafaultPage());
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

    public void setRememberMe(final boolean remember) {
        rememberMe = remember;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

}
