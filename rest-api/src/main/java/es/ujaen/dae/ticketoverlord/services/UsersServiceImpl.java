package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.annotations.LoggedUserOperation;
import es.ujaen.dae.ticketoverlord.daos.UsersDAO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.exceptions.services.users.NoUserFoundException;
import es.ujaen.dae.ticketoverlord.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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
    public UserDTO addNewUser(UserDTO userDTO) {

        User user = new User(userDTO.getName(), userDTO.getPassword());
        user.setUuidToken(UUID.randomUUID().toString());
        usersDAO.insertUser(user);
        return new UserDTO(user);
    }

    @Override
    public UserDTO modifyUser(UserDTO userDTO) {

        User user = usersDAO.selectUserById(userDTO.getUserId());
        if (user != null) {
            user.setName(userDTO.getName());
            user.setPassword(userDTO.getPassword());
            usersDAO.updateUser(user);
            return new UserDTO(user);
        } else {
            throw new NoUserFoundException();
        }
    }

    @Override
    public void deleteUser(UserDTO userDTO) {

        User user = usersDAO.selectUserById(userDTO.getUserId());
        if (user != null) {
            usersDAO.deleteUser(user);
        } else {
            throw new NoUserFoundException();
        }
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
    public List<UserDTO> getUsers() {

        List<UserDTO> userDTOs = new ArrayList<>();
        List<User> users = usersDAO.selectAllUsers();

        for (User user : users) {
            userDTOs.add(getUserAsDTO(user));
        }
        return userDTOs;
    }

    @Override
    public UserDTO getUser(UserDTO userToQuery) {
        return getUserAsDTO(usersDAO.selectUserByName(userToQuery.getName()));
    }

    @Override
    public UserDTO getUser(Integer userId) {
        return getUserAsDTO(usersDAO.selectUserById(userId));
    }

    private UserDTO getUserAsDTO(User user) {
        if (user != null) {
            UserDTO userDTO = new UserDTO(user);
            if (isAdmin(userDTO)) {
                userDTO.setAdmin(true);
            }
            return userDTO;
        }
        throw new NoUserFoundException();
    }
}
