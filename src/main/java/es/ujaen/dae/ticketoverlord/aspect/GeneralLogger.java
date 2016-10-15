package es.ujaen.dae.ticketoverlord.aspect;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneralLogger {
    private Logger log;

    public GeneralLogger() {
        log = LoggerFactory.getLogger(GeneralLogger.class);
    }

    public void logMethodExecution(JoinPoint joinpoint) {
        log.debug(joinpoint.getSignature().toShortString());
    }

    public void logAfterException(JoinPoint joinpoint, Exception exception) {
        log.warn("Exception on: " + joinpoint.getSignature().toShortString());
        log.warn(exception.getClass().toString() + " :: " + exception.getMessage());
    }
}
