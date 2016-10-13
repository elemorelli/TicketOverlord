package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.UserDTO;

public interface UsersService {
    void addNewUser(UserDTO user);

    Boolean userExists(UserDTO user);

    boolean authenticateUser(UserDTO user);

    UserDTO getUserData(UserDTO user);
}
