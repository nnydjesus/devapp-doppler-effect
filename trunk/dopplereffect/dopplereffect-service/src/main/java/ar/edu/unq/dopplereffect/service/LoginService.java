package ar.edu.unq.dopplereffect.service;

import ar.edu.unq.dopplereffect.persistence.repositories.UserRepositoryImpl;
import ar.edu.unq.dopplereffect.user.User;

/**
 */
public class LoginService implements Service {
    private static final long serialVersionUID = 1L;

    private UserRepositoryImpl repository;

    public User login(final String userName, final String password) {
        return this.getRepository().login(userName, password);
    }

    public void signUp(final String userName, final String password) {
        this.getRepository().signUp(userName, password);
    }

    public void setRepository(final UserRepositoryImpl repository) {
        this.repository = repository;
    }

    public UserRepositoryImpl getRepository() {
        return repository;
    }
}
