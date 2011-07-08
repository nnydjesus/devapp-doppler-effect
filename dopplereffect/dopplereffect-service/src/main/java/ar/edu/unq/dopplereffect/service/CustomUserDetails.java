package ar.edu.unq.dopplereffect.service;

import java.util.List;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

import ar.edu.unq.dopplereffect.user.User;

/**
 * Wrapper de {@link User} que sirve para interactuar con la
 * autenticacion/autorizacion de Spring.
 */
public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = -8809343057307326928L;

    private User user;

    public CustomUserDetails(final User user) {
        this.user = user;
    }

    @Override
    public GrantedAuthority[] getAuthorities() {
        List<String> roles = user.getRoles();
        GrantedAuthority[] springRoles = new GrantedAuthority[roles.size()];
        for (int i = 0; i < roles.size(); i++) {
            springRoles[i] = new GrantedAuthorityImpl(roles.get(i));
        }
        return springRoles;
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
