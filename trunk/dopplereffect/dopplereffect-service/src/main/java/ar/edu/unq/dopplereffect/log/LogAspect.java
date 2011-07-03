package ar.edu.unq.dopplereffect.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.service.mail.MailService;

/**
 */
@Aspect
public class LogAspect {

    private static final Logger LOG = Logger.getLogger(LogAspect.class);

    private MailService mailService;

    @Around("execution(* ar.edu.unq.dopplereffect.service.*.*.*(..))")
    public Object aroundSearch(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            LOG.info(pjp.getTarget() + " execute " + pjp.getSignature().getName());
            return pjp.proceed();
        } catch (UserException e) {
            LOG.error(pjp.getTarget() + " execute " + pjp.getSignature().getName() + " with error " + e);
            this.getMailService().sendErrorMail(e, "nnydjesus@gmail.com");
            throw e;
        } catch (Exception e) {
            LOG.error(pjp.getTarget() + " execute " + pjp.getSignature().getName() + " with error " + e);
            this.getMailService().sendErrorMail(e, "nnydjesus@gmail.com");
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
