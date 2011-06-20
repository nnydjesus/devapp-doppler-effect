package ar.edu.unq.dopplereffect.presentation;

import java.io.Serializable;

import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.presentation.util.CallBack;
import ar.edu.unq.dopplereffect.service.LoginService;
import ar.edu.unq.dopplereffect.user.User;
import ar.edu.unq.tpi.util.common.HashUtils;

/**
 * Responsable de realizar los procesos de autenticacion.
 */
public class Authenticate implements Serializable {

    private static final long serialVersionUID = 8275619832282562770L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private LoginService loginService;

    /* *************************** CONSTRUCTORS *************************** */

    /* **************************** ACCESSORS ***************************** */

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(final LoginService loginService) {
        this.loginService = loginService;
    }

    /* **************************** OPERATIONS **************************** */

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
