package es.ujaen.dae.ticketoverlord.resources.v1;

import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.exceptions.services.ForbiddenAccessException;
import es.ujaen.dae.ticketoverlord.exceptions.services.UnauthorizedAccessException;
import es.ujaen.dae.ticketoverlord.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class ApiResource {
    @Autowired
    private UsersService usersService;

    protected void verifyAuthenticatedUser(String username) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            throw new UnauthorizedAccessException();
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String authenticatedUser = userDetails.getUsername();
        boolean isAdmin = false;
        for (GrantedAuthority role : userDetails.getAuthorities()) {
            if (role.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        if (!isAdmin && !authenticatedUser.equalsIgnoreCase(username)) {
            throw new ForbiddenAccessException();
        }
    }

    protected void verifyAuthenticatedUser(Integer userId) {

        UserDTO user = usersService.getUser(userId);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String authenticatedUser = userDetails.getUsername();

        if (!authenticatedUser.equalsIgnoreCase("admin") && !authenticatedUser.equalsIgnoreCase(user.getUsername())) {
            throw new ForbiddenAccessException();
        }
    }
}
