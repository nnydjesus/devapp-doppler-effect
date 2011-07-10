package ar.edu.unq.dopplereffect.presentation;

import org.apache.wicket.Request;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;

import ar.edu.unq.tpi.util.common.HashUtils;

public class DopplerEffectAuthenticatedWebSession extends AuthenticatedWebSession {

    private static final long serialVersionUID = -4676319381093179827L;

    @SpringBean(name = "authenticationManager")
    private AuthenticationManager authenticationManager;

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public DopplerEffectAuthenticatedWebSession(final Request request) {
        super(request);
        this.injectDependencies();
        this.ensureDependenciesNotNull();
    }

    private void ensureDependenciesNotNull() {
        if (this.getAuthenticationManager() == null) {
            throw new IllegalStateException("AdminSession requires an authenticationManager.");
        }
    }

    private void injectDependencies() {
        InjectorHolder.getInjector().inject(this);
    }

    @Override
    public boolean authenticate(final String username, final String password) {
        String hashedPassword = HashUtils.hash(password);
        boolean authenticated = false;
        try {
            Authentication authentication = this.getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(username, hashedPassword));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            authenticated = authentication.isAuthenticated();
        } catch (AuthenticationException e) {
            authenticated = false;
        }
        return authenticated;
    }

    @Override
    public Roles getRoles() {
        Roles roles = new Roles();
        this.getRolesIfSignedIn(roles);
        return roles;
    }

    private void getRolesIfSignedIn(final Roles roles) {
        if (this.isSignedIn()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                this.addRolesFromAuthentication(roles, authentication);
            }
        }
    }

    private void addRolesFromAuthentication(final Roles roles, final Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            roles.add(authority.getAuthority());
        }
    }

}