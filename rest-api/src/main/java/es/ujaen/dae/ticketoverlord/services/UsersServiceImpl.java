package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.daos.UsersDAO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.exceptions.services.users.NoUserFoundException;
import es.ujaen.dae.ticketoverlord.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component("UsersService")
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDTO addNewUser(UserDTO userDTO) {

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setUuidToken(UUID.randomUUID().toString());
        user.setEnabled(true);
        usersDAO.insertUser(user);
        return new UserDTO(user);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public UserDTO modifyUser(UserDTO userDTO) {

        User user = usersDAO.selectUserById(userDTO.getUserId());
        if (user != null) {
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            usersDAO.updateUser(user);
            return new UserDTO(user);
        } else {
            throw new NoUserFoundException();
        }
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void deleteUser(UserDTO userDTO) {

        User user = usersDAO.selectUserById(userDTO.getUserId());
        if (user != null) {
            usersDAO.deleteUser(user);
        } else {
            throw new NoUserFoundException();
        }
    }

    @Override
    @Secured("ROLE_ADMIN")
    public List<UserDTO> getUsers() {

        List<UserDTO> userDTOs = new ArrayList<>();
        List<User> users = usersDAO.selectAllUsers();

        for (User user : users) {
            userDTOs.add(getUserAsDTO(user));
        }
        return userDTOs;
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public UserDTO getUser(UserDTO userToQuery) {
        try {
            return getUserAsDTO(usersDAO.selectUserByName(userToQuery.getUsername()));
        } catch (NoResultException e) {
            throw new NoUserFoundException();
        }
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_ADMIN"})
    public UserDTO getUser(Integer userId) {
        // TODO: Check user rights
        return getUserAsDTO(usersDAO.selectUserById(userId));
    }

    private UserDTO getUserAsDTO(User user) {
        if (user != null) {
            return new UserDTO(user);
        }
        throw new NoUserFoundException();
    }
}
