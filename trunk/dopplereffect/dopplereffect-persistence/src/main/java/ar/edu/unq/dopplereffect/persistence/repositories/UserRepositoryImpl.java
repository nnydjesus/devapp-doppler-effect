package ar.edu.unq.dopplereffect.persistence.repositories;

import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.user.User;

/**
 */
public class UserRepositoryImpl extends HibernatePersistentRepository<User> {
    private static final long serialVersionUID = 1L;

    protected static final String USER_NAME_ALREADY_EXIST = "exception.userNameAlreadyExist";

    protected static final String USER_NOT_EXIST = "exception.userNotExist";

    protected static final String INCORRECT_PASSWORD = "exception.incorrectPassword";

    public UserRepositoryImpl() {
        super(User.class);
    }

    public void signUp(final String userName, final String password, final String email) {
        User user = this.getByName(userName);
        if (user != null) {
            throw new UserException(USER_NAME_ALREADY_EXIST);
        }
        this.save(new User(userName, password, email));
    }

    public User login(final String userName, final String password) {
        User user = this.getByName(userName);
        if (user == null) {
            throw new UserException(USER_NOT_EXIST);
        }
        // despues ver en donde se tiene que ver la validacion de la
        // contrasenia
        // hasheada
        if (!user.getPassword().equals(password)) {
            throw new UserException(INCORRECT_PASSWORD);
        }
        return user;
    }

}
