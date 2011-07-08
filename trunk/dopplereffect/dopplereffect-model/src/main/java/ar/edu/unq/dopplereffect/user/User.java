package ar.edu.unq.dopplereffect.user;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unq.dopplereffect.entity.Entity;

public class User extends Entity {

    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private String name;

    private String password;

    private String email;

    private List<String> roles;

    /* *************************** CONSTRUCTORS *************************** */

    public User(final String name, final String password, final String email, final List<String> roles) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public User(final String username, final String password, final String email) {
        this(username, password, email, new LinkedList<String>());
    }

    public User() {
        super();
        roles = new LinkedList<String>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(final List<String> roles) {
        this.roles = roles;
    }
}
