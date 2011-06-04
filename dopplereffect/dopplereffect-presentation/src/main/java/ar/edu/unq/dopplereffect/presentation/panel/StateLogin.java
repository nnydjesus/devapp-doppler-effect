package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 */
public enum StateLogin {
    LOGIN {

        @Override
        public void submit(final String userName, final String password, final LoginPanel panel,
                final AjaxRequestTarget target) {
            panel.submitLogin(userName, password, target);
        }

        @Override
        public void onLink(final LoginPanel panel) {
            panel.gotoRegister();
        }
    },
    REGISTER {

        @Override
        public void submit(final String userName, final String password, final LoginPanel panel,
                final AjaxRequestTarget target) {
            panel.submitRegister(userName, password, target);
        }

        @Override
        public void onLink(final LoginPanel panel) {
            panel.gotoLogin();
        }
    };

    public abstract void submit(String userName, String password, LoginPanel panel, AjaxRequestTarget target);

    public abstract void onLink(LoginPanel panel);

}
