package ar.edu.unq.dopplereffect.mail;

import java.util.Locale;

import org.apache.commons.lang.exception.ExceptionUtils;

import ar.edu.unq.dopplereffect.service.Service;
import ar.edu.unq.tpi.commons.configuration.jfig.JFigConfiguration;
import ar.edu.unq.tpi.util.services.email.TemplateSource;
import ar.edu.unq.tpi.util.services.services.ServiceLocator;

/**
 */
public class MailService implements Service {
    private static final long serialVersionUID = 1L;

    private ar.edu.unq.tpi.util.services.email.MailService mailService = ServiceLocator
            .locate(ar.edu.unq.tpi.util.services.email.MailService.class);

    public void sendErrorMail(final Throwable e, final String... destinatario) {
        TemplateSource body = new TemplateSource("mail/errorMail", LocaleManager.getLocaleManager().getLocale());
        body.addObject("exception", ExceptionUtils.getStackTrace(e));
        mailService.send(body, "Error in DooplerEffect", destinatario);

    }

    public void sendSignUpMail(final String email, final String userName, final Locale locale) {
        TemplateSource body = new TemplateSource("mail/signUp", locale);
        body.addObject("user", userName);
        String location = JFigConfiguration.getInstance().getString("appLocation", "uri");
        body.addObject("link", location);
        mailService.send(body, "Registration", email);
    }

}
