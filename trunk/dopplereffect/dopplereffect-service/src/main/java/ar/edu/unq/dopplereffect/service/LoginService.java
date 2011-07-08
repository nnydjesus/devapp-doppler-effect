package ar.edu.unq.dopplereffect.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.dopplereffect.mail.LocaleManager;
import ar.edu.unq.dopplereffect.mail.MailService;
import ar.edu.unq.dopplereffect.persistence.repositories.UserRepositoryImpl;
import ar.edu.unq.dopplereffect.user.User;

public class LoginService implements Service, UserDetailsService {

    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private UserRepositoryImpl repository;

    private MailService mailService;

    /* *************************** CONSTRUCTORS *************************** */

    /* **************************** ACCESSORS ***************************** */

    public UserRepositoryImpl getRepository() {
        return repository;
    }

    public void setRepository(final UserRepositoryImpl repository) {
        this.repository = repository;
    }

    public MailService getMailService() {
        return mailService;
    }

    public void setMailService(final MailService mailService) {
        this.mailService = mailService;
    }

    /* **************************** OPERATIONS **************************** */

    @Transactional
    public void signUpAdmin(final String username, final String password, final String email) {
        this.signUp(username, password, email, Arrays.asList(this.getAllRoles()));
    }

    @Transactional
    public void signUpUser(final String username, final String password, final String email) {
        this.signUp(username, password, email, Arrays.asList(this.getDefaultRoleForNewUser()));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException, DataAccessException {
        User user = this.getRepository().getByName(username);
        return new CustomUserDetails(user == null ? new User() : user);
    }

    /* ************************* PRIVATE METHODS ************************** */

    private void signUp(final String username, final String password, final String email, final List<String> roles) {
        this.getRepository().signUp(username, password, email, roles);
        mailService.sendSignUpMail(email, username, LocaleManager.getLocaleManager().getLocale());
    }

    private String getDefaultRoleForNewUser() {
        return "ROLE_USER";
    }

    private String getAdminRoleName() {
        return "ROLE_ADMIN";
    }

    private String[] getAllRoles() {
        return new String[] { this.getAdminRoleName(), this.getDefaultRoleForNewUser() };
    }
}
