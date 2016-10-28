package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.annotations.LoggedUserOperation;
import es.ujaen.dae.ticketoverlord.daos.UsersDAO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component("UsersService")
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersDAO usersDAO;
    private final Set<String> authenticatedUsers; // TODO: Es una pseudo-sesión. Hay que removerla del servicio
    private final String adminToken = "3842affe-750b-4fa1-8120-0433a21a2f74"; // TODO: Debe estar en la DB

    public UsersServiceImpl() {
        authenticatedUsers = new HashSet<>();
    }

    @Override
    public void addNewUser(UserDTO user) {

        User nuevoUser = new User(user.getName(), user.getPassword());
        nuevoUser.setUuidToken(UUID.randomUUID().toString());
        usersDAO.insertUser(nuevoUser);
    }

    @Override
    public boolean userExists(UserDTO userToQuery) {

        return usersDAO.selectUserByName(userToQuery.getName()) != null;
    }

    @Override
    public boolean authenticateUser(UserDTO userToValidate) {

        User user = usersDAO.selectUserByName(userToValidate.getName());

        if (user != null && user.getPassword().equals(userToValidate.getPassword())) {
            authenticatedUsers.add(user.getUuidToken());
            return true;
        } else {
            return false;
        }
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
    public UserDTO getUserData(UserDTO userToQuery) {

        User user = usersDAO.selectUserByName(userToQuery.getName());

        if (user != null) {
            UserDTO foundUser = new UserDTO(user);
            if (isAdmin(foundUser)) {
                foundUser.setAdmin(true);
            }
            return foundUser;
        }
        return null;
    }
}
