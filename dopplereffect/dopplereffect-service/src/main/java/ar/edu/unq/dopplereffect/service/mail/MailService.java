package ar.edu.unq.dopplereffect.service.mail;

import org.apache.commons.lang.exception.ExceptionUtils;

import ar.edu.unq.dopplereffect.service.Service;
import ar.edu.unq.tpi.util.services.email.TemplateSource;
import ar.edu.unq.tpi.util.services.services.ServiceLocator;

/**
 * TODO: description
 */
public class MailService implements Service {
    private static final long serialVersionUID = 1L;

    private ar.edu.unq.tpi.util.services.email.MailService mailService = ServiceLocator
            .locate(ar.edu.unq.tpi.util.services.email.MailService.class);

    public void sendErrorMail(final Throwable e, final String... destinatario) {
        TemplateSource body = new TemplateSource("mail/errorMail.vm");
        body.addObject("exception", ExceptionUtils.getStackTrace(e));
        mailService.send(body, "Error in DooplerEffect", destinatario);
    }

}
