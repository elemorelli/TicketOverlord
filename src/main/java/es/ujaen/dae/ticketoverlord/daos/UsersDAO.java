package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.User;

public interface UsersDAO {
    User selectUserByName(String userName);

    void insertUser(User user);

    void updateUser(User user);

    void delete(User user);
}
