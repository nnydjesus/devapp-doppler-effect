package ar.edu.unq.dopplereffect.service;

import ar.edu.unq.dopplereffect.mail.LocaleManager;
import ar.edu.unq.dopplereffect.mail.MailService;
import ar.edu.unq.dopplereffect.persistence.repositories.UserRepositoryImpl;
import ar.edu.unq.dopplereffect.user.User;

/**
 */
public class LoginService implements Service {
    private static final long serialVersionUID = 1L;

    private UserRepositoryImpl repository;

    private MailService mailService;

    public User login(final String userName, final String password) {
        return this.getRepository().login(userName, password);
    }

    public void signUp(final String userName, final String password, final String email) {
        this.getRepository().signUp(userName, password, email);
        mailService.sendSignUpMail(email, userName, LocaleManager.getLocaleManager().getLocale());
    }

    public void setRepository(final UserRepositoryImpl repository) {
        this.repository = repository;
    }

    public UserRepositoryImpl getRepository() {
        return repository;
    }

    public void setMailService(final MailService mailService) {
        this.mailService = mailService;
    }

    public MailService getMailService() {
        return mailService;
    }
}
