package ar.edu.unq.dopplereffect.user;

import ar.edu.unq.dopplereffect.entity.Entity;

public class User extends Entity {

    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private String name;

    private String password;

    /* *************************** CONSTRUCTORS *************************** */

    public User(final String userName, final String password2) {
        super();
        name = userName;
        password = password2;
    }

    public User() {
        super();
    }

    /* **************************** ACCESSORS ***************************** */

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
