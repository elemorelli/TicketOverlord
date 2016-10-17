package es.ujaen.dae.ticketoverlord.aspects;

import es.ujaen.dae.ticketoverlord.client.ConsoleClient;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.services.UsersService;
import org.aspectj.lang.ProceedingJoinPoint;

public class UserValidator {
    public Object verifyLoggedUser(ProceedingJoinPoint joinPoint) throws Exception {
        UserDTO user = ConsoleClient.getAuthenticatedUser();

        if (user != null) {
            UsersService usersService = (UsersService) ConsoleClient.getAppContext().getBean("usersService");
//            user = usersService.getUserData(user);
            if (usersService.isUserAuthenticated(user)) {
                try {
                    return joinPoint.proceed();
                } catch (Throwable throwable) {
                    return throwable;
                }
            } else {
                throw new Exception("User " + user.getName() + " tried to do an operation that requires authentication");
            }
        } else {
            throw new Exception("Unknown user tried to do an operation that requires authentication");
        }
    }

    public Object verifyAdminOnly(ProceedingJoinPoint joinPoint) throws Exception {
        UserDTO user = ConsoleClient.getAuthenticatedUser();

        if (user != null) {
            UsersService usersService = (UsersService) ConsoleClient.getAppContext().getBean("usersService");
//            user = usersService.getUserData(user);
            if (usersService.isUserAuthenticated(user) && usersService.isAdmin(user)) {
                try {
                    return joinPoint.proceed();
                } catch (Throwable throwable) {
                    return throwable;
                }
            } else {
                throw new Exception("User " + user.getName() + " tried to do an Admininstrator operation");
            }
        } else {
            throw new Exception("Unknown user tried to do an Admininstrator operation");
        }
    }
}
