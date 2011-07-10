package ar.edu.unq.dopplereffect.mail;

import java.util.Locale;

import org.apache.commons.lang.exception.ExceptionUtils;

import ar.edu.unq.dopplereffect.service.Service;
import ar.edu.unq.tpi.commons.configuration.jfig.JFigConfiguration;
import ar.edu.unq.tpi.util.commons.exeption.UserException;
import ar.edu.unq.tpi.util.services.email.TemplateSource;
import ar.edu.unq.tpi.util.services.email.TextSource;
import ar.edu.unq.tpi.util.services.services.ServiceLocator;

/**
 */
public class MailService implements Service {
    private static final long serialVersionUID = 1L;

    private ar.edu.unq.tpi.util.services.email.MailService mailService = ServiceLocator
            .locate(ar.edu.unq.tpi.util.services.email.MailService.class);

    public void sendErrorMail(final Throwable e) {
        TemplateSource body = new TemplateSource("mail/errorMail", LocaleManager.getLocaleManager().getLocale());
        body.addObject("exception", ExceptionUtils.getStackTrace(e));
        this.sendMail(body, "Error in DooplerEffect",
                JFigConfiguration.getInstance().getString("users", "user").split(","));

    }

    public void sendSignUpMail(final String email, final String userName, final Locale locale) {
        TemplateSource body = new TemplateSource("mail/signUp", locale);
        body.addObject("user", userName);
        String location = JFigConfiguration.getInstance().getString("appLocation", "uri");
        body.addObject("link", location);
        this.sendMail(body, "Registration", email);
    }

    public void sendMail(final TextSource body, final String subject, final String... emails) {
        try {
            new Thread() {
                @Override
                public void run() {
                    MailService.this.getMailService().send(body, subject, emails);
                }
            }.start();
        } catch (Exception e) {
            throw new UserException(e);
        }
    }

    public void setMailService(final ar.edu.unq.tpi.util.services.email.MailService mailService) {
        this.mailService = mailService;
    }

    public ar.edu.unq.tpi.util.services.email.MailService getMailService() {
        return mailService;
    }

}
