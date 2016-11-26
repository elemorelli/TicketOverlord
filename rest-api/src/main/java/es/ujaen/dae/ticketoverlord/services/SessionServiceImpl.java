package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.annotations.LoggedUserOperation;
import es.ujaen.dae.ticketoverlord.daos.UsersDAO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.exceptions.services.UnauthorizedAccessException;
import es.ujaen.dae.ticketoverlord.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component("SessionService")
public class SessionServiceImpl implements SessionService {
    @Autowired
    private UsersDAO usersDAO;
    private final Set<String> authenticatedUsers; // TODO: Es una pseudo-sesión. Hay que removerla del servicio

    public SessionServiceImpl() {
        authenticatedUsers = new HashSet<>();
    }

    @Override
    public UserDTO authenticateUser(UserDTO userDTO) {

        User user = usersDAO.selectUserByName(userDTO.getName());

        if (user != null && user.getPassword().equals(userDTO.getPassword())) {
            authenticatedUsers.add(user.getUuidToken());
            return new UserDTO(user);
        } else {
            throw new UnauthorizedAccessException();
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
}
