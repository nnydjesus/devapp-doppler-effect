package ar.edu.unq.dopplereffect.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

/**
 */
@Aspect
public class AuditAspect {

    private static final Logger LOG = Logger.getLogger(AuditAspect.class);

    @Around("execution(* ar.edu.unq.dopplereffect.service.*.*.*(..))")
    public Object aroundSearch(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            if (!((MethodSignature) pjp.getSignature()).getMethod().isAnnotationPresent(NotLoggable.class)) {
                LOG.info(pjp.getTarget() + " execute " + pjp.getSignature().getName());
            }
            return pjp.proceed();
        } catch (Exception e) {
            LOG.error(pjp.getTarget() + " execute " + pjp.getSignature().getName() + " with error " + e);
            throw e;
        }
    }
}
