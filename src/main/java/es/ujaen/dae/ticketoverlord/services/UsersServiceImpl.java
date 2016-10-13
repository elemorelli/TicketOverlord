package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.models.User;

import java.util.List;
import java.util.UUID;

public class UsersServiceImpl implements UsersService {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public void addNewUser(UserDTO user) {

        User nuevoUser = new User(user.getName(), user.getPassword());
        nuevoUser.setUuidToken(UUID.randomUUID().toString());
        users.add(nuevoUser);
    }

    @Override
    public Boolean userExists(UserDTO userToQuery) {

        for (User user : this.users) {
            if (user.getName().equalsIgnoreCase(userToQuery.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean authenticateUser(UserDTO userToValidate) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(userToValidate.getName())
                    && user.getPassword().equals(userToValidate.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UserDTO getUserData(UserDTO userToQuery) {

        for (User user : this.users) {
            if (user.getName().equalsIgnoreCase(userToQuery.getName())) {
                UserDTO foundUser = new UserDTO(user);
                if (foundUser.getName().equalsIgnoreCase("Administrador")) {
                    foundUser.setAdmin(true);
                }
                return foundUser;
            }
        }
        return null;
    }
}
