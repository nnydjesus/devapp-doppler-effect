package ar.edu.unq.dopplereffect.presentation;

import org.apache.wicket.Request;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;

public class WicketWebSession extends AuthenticatedWebSession {
    private static final long serialVersionUID = 1L;

    public WicketWebSession(final Request request) {
        super(request);
    }

    @Override
    public boolean authenticate(final String username, final String password) {
        return true;
    }

    @Override
    public Roles getRoles() {
        Roles roles = new Roles();
        roles.add("ADMIN");
        return roles;
    }

}