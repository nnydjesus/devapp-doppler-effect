package ar.edu.unq.dopplereffect.presentation;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

public class WicketWebSession extends WebSession {
    private static final long serialVersionUID = 1L;
    /** Trivial user representation */
    private String user;

    /**
     * Constructor
     * 
     * @param request
     *            The current request object
     */
    protected WicketWebSession(final Request request) {
        super(request);
    }

    /**
     * Checks the given username and password, returning a User object if if the
     * username and password identify a valid user.
     * 
     * @param username
     *            The username
     * @param password
     *            The password
     * @return True if the user was authenticated
     */
    public final boolean authenticate(final String username, final String password) {
        if (user == null) {
            // Trivial password "db"
            if ("wicket".equalsIgnoreCase(username) && "wicket".equalsIgnoreCase(password)) {
                user = username;
            }
        }

        return user != null;
    }

    /**
     * @return True if user is signed in
     */
    public boolean isSignedIn() {
        return user != null;
    }

    /**
     * @return User
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user
     *            New user
     */
    public void setUser(final String user) {
        this.user = user;
    }
}

