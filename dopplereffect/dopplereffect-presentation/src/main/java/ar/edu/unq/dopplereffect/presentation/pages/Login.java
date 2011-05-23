package ar.edu.unq.dopplereffect.presentation.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import ar.edu.unq.dopplereffect.presentation.util.CallBack;
import ar.edu.unq.dopplereffect.project.Project;
import ar.edu.unq.dopplereffect.service.LoginService;
import ar.edu.unq.dopplereffect.user.User;

public class Login extends StylePage {

    /**
     * Login page constituents are the same as Login.html except that it is made
     * up of equivalent Wicket components
     */

    private TextField<String> userIdField;

    private PasswordTextField passField;

    private LoginService service;

    private WebPage pageXXX; // no se que ponerle

    public Login() {
        super();
        service = new LoginService();
        // pageXXX = new ProjectSearchPage(this);
        // pageXXX = new SkillSearchPage(this);
        pageXXX = new Home();
        this.add(this.createForm("loginForm", new Model("")));
    }

    @Override
    protected Form createForm(final String formName, final Object formModel) {
        Form<Project> form = new Form<Project>("loginForm");
        userIdField = new RequiredTextField<String>("name", new Model(""));
        passField = new PasswordTextField("password", new Model(""));

        /* Make sure that password field shows up during page re-render * */

        passField.setResetPassword(true);

        form.add(userIdField);
        form.add(passField);
        form.add(this.setFeedbackPanel(new FeedbackPanel("feedbackPanel")));
        form.add(this.createSubmitButton());
        return form;
    }

    private Button createSubmitButton() {
        return new Button("submit") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                String userName = userIdField.getDefaultModelObjectAsString();
                String password = passField.getDefaultModelObjectAsString();
                User user = service.login(userName, password);
                if (Login.this.getCallBack() != null) {
                    Login.this.getCallBack().execute(user);
                }
            }
        };
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