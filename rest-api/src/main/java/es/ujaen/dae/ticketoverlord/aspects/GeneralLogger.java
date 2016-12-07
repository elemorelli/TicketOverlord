package es.ujaen.dae.ticketoverlord.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component("LoggingAspect")
public class GeneralLogger {
    private final Logger log;

    public GeneralLogger() {
        log = LoggerFactory.getLogger(GeneralLogger.class);
    }

//    @Before(value = "execution(* es.ujaen.dae.ticketoverlord..* (..))")
    public void logMethodExecution(JoinPoint joinpoint) {
        log.debug(joinpoint.getSignature().toShortString());
    }

//    @AfterThrowing(value = "execution(* es.ujaen.dae.ticketoverlord..* (..))", throwing = "exception")
    public void logAfterException(JoinPoint joinpoint, Exception exception) {
        log.warn("Exception on: " + joinpoint.getSignature().toShortString());
        log.warn(exception.getClass().toString() + " :: " + exception.getMessage());
    }
}
