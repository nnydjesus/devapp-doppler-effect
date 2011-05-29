package ar.edu.unq.dopplereffect.presentation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 */
@Aspect
public class ManagerExceptionAspect {

    @Around("execution(* UserException(..))")
    public Object aroundSaludar(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } finally {
        }

    }
}
