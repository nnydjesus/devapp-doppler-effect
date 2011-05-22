package ar.edu.unq.dopplereffect.service;

import ar.edu.unq.dopplereffect.user.User;

/**
 */
public class LoginService extends ServiceImpl<User> {
    private static final long serialVersionUID = 1L;

    public User login(final String userName, final String password) {
        return new User(userName, password);
    }

}
