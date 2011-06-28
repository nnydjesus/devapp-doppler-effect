package ar.edu.unq.dopplereffect.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 */
@Aspect
public class LogAspect {
	
	private static final Logger LOG = Logger.getLogger(LogAspect.class);

    @Around("execution(* ar.edu.unq.dopplereffect.service.*.*.*(..))")
    public Object aroundSearch(final ProceedingJoinPoint pjp) throws Throwable {
    	try {
    		LOG.debug(pjp.getTarget() + " execute "+pjp.getSignature().getName());
    		return pjp.proceed();
		} catch (Exception e) {
    		LOG.error(pjp.getTarget() + " execute "+pjp.getSignature().getName()+ " with error "+e);
			throw e;
		}
    }
}
