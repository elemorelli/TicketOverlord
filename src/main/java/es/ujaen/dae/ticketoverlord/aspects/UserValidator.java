package es.ujaen.dae.ticketoverlord.aspects;

import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.exceptions.UnauthorizedAccessException;
import es.ujaen.dae.ticketoverlord.services.UsersService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

public class UserValidator {
    @Autowired
    private UsersService usersService;

    public Object verifyLoggedUser(ProceedingJoinPoint joinPoint, UserDTO user) throws UnauthorizedAccessException {

        if (user != null) {
            if (usersService.isUserAuthenticated(user)) {
                try {
                    return joinPoint.proceed();
                } catch (Throwable throwable) {
                    return throwable;
                }
            } else {
                throw new UnauthorizedAccessException("User " + user.getName() + " tried to do an operation that requires authentication");
            }
        } else {
            throw new UnauthorizedAccessException("Unknown user tried to do an operation that requires authentication");
        }
    }

    public Object verifyAdminOnly(ProceedingJoinPoint joinPoint, UserDTO user) throws UnauthorizedAccessException {

        if (user != null) {
            if (usersService.isUserAuthenticated(user) && usersService.isAdmin(user)) {
                try {
                    return joinPoint.proceed();
                } catch (Throwable throwable) {
                    return throwable;
                }
            } else {
                throw new UnauthorizedAccessException("User " + user.getName() + " tried to do an Admininstrator operation");
            }
        } else {
            throw new UnauthorizedAccessException("Unknown user tried to do an Admininstrator operation");
        }
    }
}
