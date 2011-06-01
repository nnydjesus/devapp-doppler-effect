package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 */
public enum StateLogin {
    LOGIN {

		@Override
		public void submit(String userName, String password, LoginPanel panel, AjaxRequestTarget target) {
			panel.submitLogin(userName, password, target);
		}

		@Override
		public void onLink(LoginPanel panel) {
			panel.gotoRegister();
		}	
	}, 
	REGISTER {

		@Override
		public void submit(String userName, String password, LoginPanel panel, AjaxRequestTarget target) {
			panel.submitRegister(userName, password, target);
		}

		@Override
		public void onLink(LoginPanel panel) {
			panel.gotoLogin();
		}
	};
    
    abstract public void submit(String userName, String password, LoginPanel panel, AjaxRequestTarget target);
    abstract public void onLink(LoginPanel panel );
    

}

