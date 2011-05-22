package ar.edu.unq.dopplereffect.user;

import ar.edu.unq.dopplereffect.entity.Entity;

public class User extends Entity {
    private static final long serialVersionUID = 1L;

    private String name;

    private String password;

    public User(final String userName, final String password2) {
        name = userName;
        password = password2;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
