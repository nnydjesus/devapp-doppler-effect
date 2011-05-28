package ar.edu.unq.dopplereffect.service;

import ar.edu.unq.dopplereffect.persistence.repositories.UserRepositoryImpl;
import ar.edu.unq.dopplereffect.user.User;

/**
 */
public class LoginService extends ServiceImpl<User> {
    private static final long serialVersionUID = 1L;

    public User login(final String userName, final String password) {
        return this.getUserRepository().login(userName, password);
        // return new User(userName, password);
    }

    public void signUp(final String userName, final String password) {
        this.getUserRepository().signUp(userName, password);
    }

    public UserRepositoryImpl getUserRepository() {
        return (UserRepositoryImpl) this.getRepository();
    }

}
