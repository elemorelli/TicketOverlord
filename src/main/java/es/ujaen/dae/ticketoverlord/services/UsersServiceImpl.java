package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.annotations.LoggedUserOperation;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.models.User;

import java.util.*;

public class UsersServiceImpl implements UsersService {
    private List<User> users;
    private Set<String> authenticatedUsers;
    private String adminToken = "3842affe-750b-4fa1-8120-0433a21a2f74";

    public UsersServiceImpl() {
        users = new ArrayList<>();
        authenticatedUsers = new HashSet<>();
    }

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
                authenticatedUsers.add(user.getUuidToken());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isUserAuthenticated(UserDTO user) {
        return authenticatedUsers.contains(user.getUuidToken());
    }

    @Override
    @LoggedUserOperation
    public void logoutUser(UserDTO user) {
        authenticatedUsers.remove(user.getUuidToken());
    }

    @Override
    @LoggedUserOperation
    public boolean isAdmin(UserDTO user) {
        return user.getUuidToken().equals(this.adminToken);
    }

    @Override
    // @LoggedUserOperation
    public UserDTO getUserData(UserDTO userToQuery) {

        for (User user : this.users) {
            if (user.getName().equalsIgnoreCase(userToQuery.getName())) {
                UserDTO foundUser = new UserDTO(user);
                if (isAdmin(foundUser)) {
                    foundUser.setAdmin(true);
                }
                return foundUser;
            }
        }
        return null;
    }
}
