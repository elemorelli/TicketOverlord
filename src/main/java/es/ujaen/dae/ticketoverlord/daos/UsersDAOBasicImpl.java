package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersDAOBasicImpl implements UsersDAO {
    private List<User> users;

    public UsersDAOBasicImpl() {
        users = new ArrayList<>();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public User selectUserByName(String userName) {

        for (User user : users) {
            if (user.getName().equalsIgnoreCase(userName)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void insertUser(User user) {
        users.add(user);
    }

    @Override
    public void updateUser(User user) {
        // TODO
    }

    @Override
    public void delete(User user) {
        // TODO
    }
}
