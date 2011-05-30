package ar.edu.unq.dopplereffect.presentation;

import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.presentation.util.CallBack;
import ar.edu.unq.dopplereffect.service.LoginService;
import ar.edu.unq.dopplereffect.user.User;
import ar.edu.unq.tpi.util.common.HashUtils;

public class Authenticate {

    @SpringBean(name = "authenticate")
    private LoginService loginService;

    private Authenticate() {
        App.loginService = this;
    }

    public void setLoginService(final LoginService loginService) {
        this.loginService = loginService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void login(final String userName, final String password, final CallBack<User> callback,
            final CallBack<UserException> errorCallback) {
        try {
            User login = loginService.login(userName, HashUtils.hash(password));
            callback.execute(login);
        } catch (UserException e) {
            errorCallback.execute(e);
        }
    }

    public void signUp(final String userName, final String password, final CallBack<Object> callback,
            final CallBack<UserException> errorCallback) {
        try {
            loginService.signUp(userName, HashUtils.hash(password));
            callback.execute(null);
        } catch (UserException e) {
            errorCallback.execute(e);
        }
    }
}
