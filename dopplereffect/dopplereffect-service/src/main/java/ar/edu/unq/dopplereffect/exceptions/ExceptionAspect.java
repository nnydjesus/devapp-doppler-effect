package ar.edu.unq.dopplereffect.exceptions;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import ar.edu.unq.dopplereffect.mail.MailService;

/**
 */
@Aspect
public class ExceptionAspect {

    private MailService mailService;

    @Around("execution(*  ar.edu.unq.dopplereffect.*.*.*.*(..))")
    public Object aroundSearch(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            this.getMailService().sendErrorMail(e);
            throw e;
        }
    }

    public void setMailService(final MailService mailService) {
        this.mailService = mailService;
    }

    public MailService getMailService() {
        return mailService;
    }

}
