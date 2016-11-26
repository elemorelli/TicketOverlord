package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.UserDTO;

public interface SessionService {
    UserDTO authenticateUser(UserDTO user);

    boolean isUserAuthenticated(UserDTO user);

    void logoutUser(UserDTO user);
}
