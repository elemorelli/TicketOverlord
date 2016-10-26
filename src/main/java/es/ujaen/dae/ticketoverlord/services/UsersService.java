package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.UserDTO;

public interface UsersService {
    void addNewUser(UserDTO user);

    boolean userExists(UserDTO user);

    boolean authenticateUser(UserDTO user);

    boolean isUserAuthenticated(UserDTO user);

    void logoutUser(UserDTO user);

    boolean isAdmin(UserDTO user);

    UserDTO getUserData(UserDTO user);
}
