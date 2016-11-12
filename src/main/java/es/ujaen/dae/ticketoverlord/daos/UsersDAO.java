package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.User;

import java.util.List;

public interface UsersDAO {
    User selectUserById(Integer id);

    User selectUserByName(String username);

    List<User> selectAllUsers();

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
