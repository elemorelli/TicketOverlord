package es.ujaen.dae.ticketoverlord.aspects;

import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.exceptions.services.ForbiddenAccessException;
import es.ujaen.dae.ticketoverlord.exceptions.services.UnauthorizedAccessException;
import es.ujaen.dae.ticketoverlord.services.UsersService;
import org.aopalliance.aop.AspectException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component("UserValidationAspect")
public class UserValidator {
    @Autowired
    private UsersService usersService;

    @Around(value = "@annotation(es.ujaen.dae.ticketoverlord.annotations.LoggedUserOperation)")
    public Object verifyLoggedUser(ProceedingJoinPoint joinPoint) throws UnauthorizedAccessException {

        UserDTO user = getUserArgument(joinPoint);

        if (user != null) {
            if (usersService.isUserAuthenticated(user)) {
                try {
                    return joinPoint.proceed();
                } catch (Throwable throwable) {
                    return throwable;
                }
            } else {
                throw new ForbiddenAccessException("User " + user.getName() + " tried to do an operation that requires authentication");
            }
        } else {
            throw new UnauthorizedAccessException("Unknown users tried to do an operation that requires authentication");
        }
    }

    @Around(value = "@annotation(es.ujaen.dae.ticketoverlord.annotations.AdminOperation)")
    public Object verifyAdminOnly(ProceedingJoinPoint joinPoint) throws UnauthorizedAccessException {

        UserDTO user = getUserArgument(joinPoint);

        if (user != null) {
            if (usersService.isUserAuthenticated(user) && usersService.isAdmin(user)) {
                try {
                    return joinPoint.proceed();
                } catch (Throwable throwable) {
                    return throwable;
                }
            } else {
                throw new ForbiddenAccessException("User " + user.getName() + " tried to do an Admininstrator operation");
            }
        } else {
            throw new UnauthorizedAccessException("Unknown users tried to do an Admininstrator operation");
        }
    }

    private UserDTO getUserArgument(ProceedingJoinPoint joinPoint) {
        UserDTO user = null;
        Object[] signatureArgs = joinPoint.getArgs();
        for (Object signatureArg : signatureArgs) {
            if (signatureArg instanceof UserDTO) {
                user = (UserDTO) signatureArg;
                break;
            }
        }

        if (user != null) {
            return user;
        } else {
            throw new AspectException("The method " + joinPoint.getSignature().toShortString() + " requires an UserDTO argument to validate");
        }
    }
}
