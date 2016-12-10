package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.UserDTO;

import java.util.List;

public interface UsersService {
    UserDTO addNewUser(UserDTO user);

    UserDTO modifyUser(UserDTO user);

    void deleteUser(UserDTO user);

    List<UserDTO> getUsers();

    UserDTO getUser(UserDTO user);

    UserDTO getUser(Integer id);
}
