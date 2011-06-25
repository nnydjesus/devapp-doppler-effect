package ar.edu.unq.dopplereffect.presentation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 */
@Aspect
public class ManagerExceptionAspect {

    @Around("execution(* ar.edu.unq.dopplereffect.service.*.*.*(..))")
    public Object aroundSearch(final ProceedingJoinPoint pjp) throws Throwable {
        return this.proceedJoinPoint(pjp);
    }

    private Object proceedJoinPoint(final ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }
}
